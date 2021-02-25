package by.itninjas.service.impl;

import static java.time.LocalTime.MIDNIGHT;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import by.itninjas.domain.entity.DayLog;
import by.itninjas.domain.entity.Employee;
import by.itninjas.domain.entity.InOutDayLog;
import by.itninjas.domain.enums.InOutType;
import by.itninjas.domain.xml.Row;
import by.itninjas.domain.xml.RowCollection;
import by.itninjas.service.EmployeeService;
import by.itninjas.service.TimeLogService;
import by.itninjas.service.util.XmlReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TimeLogServiceImpl implements TimeLogService {

    private static final String STRING_REPLACING1 = " xmlns=\"urn:schemas-microsoft-com:xml-analysis:rowset\"";
    private static final String STRING_REPLACING2 = "&lt;span style=&quot;color:#000000&quot;&gt;";
    private static final String STRING_REPLACING3 = "&lt;/span&gt;";
    private static final String STRING_REPLACING4 = " &amp;#9658";
    private static final String STRING_REPLACING5 = "&amp;#9668 ";

    private static final LocalTime START_LUNCH_RANGE = LocalTime.NOON;
    private static final LocalTime END_LUNCH_RANGE = LocalTime.of(15, 0, 0);
    private static final LocalTime LUNCH_DURATION = LocalTime.of(1, 0, 0);

    private final EmployeeService employeeService;

    @Autowired
    public TimeLogServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<DayLog> getAllByEmployeeId(int employeeId, Pageable pageable) {
        Employee employee = employeeService
            .getById(employeeId);
        List<DayLog> dayLogs = employee.getDayLogs();

        int firstIndex = (int) pageable.getOffset();
        int endIndex = Math.min((firstIndex + pageable.getPageSize()), dayLogs.size());

        return new PageImpl<>(dayLogs.subList(firstIndex, endIndex), pageable, dayLogs.size()).getContent();
    }

    @Override
    public void uploadXml(MultipartFile file) {
        String xml;
        try {
            xml = new String(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("cannot read file", e);
        }
        xml = xml
            .replace(STRING_REPLACING1, EMPTY)
            .replace(STRING_REPLACING2, EMPTY)
            .replace(STRING_REPLACING3, EMPTY)
            .replace(STRING_REPLACING4, EMPTY)
            .replace(STRING_REPLACING5, EMPTY);

        RowCollection rowCollection = XmlReader.parse(xml, RowCollection.class);
        if (rowCollection != null && !rowCollection.getRow().isEmpty()) {
            String employeeName = rowCollection.getRow().get(0).getC0();
            List<DayLog> dayLogs = parseXmlContent(rowCollection);
            employeeService.create(employeeName, dayLogs);
        }
    }


    private List<DayLog> parseXmlContent(RowCollection rowCollection) {
        List<Row> row = rowCollection.getRow();

        return rowCollection
            .getRow()
            .stream()
            .map(Row::getC1)
            .distinct()
            .sorted()
            .map(dateAsString -> {
                LocalDate date = LocalDate.parse(dateAsString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                DayLog dayLog = new DayLog();
                dayLog.setDate(date);
                dayLog.setInOutDayLogs(
                    row
                        .stream()
                        .filter(item -> dateAsString.equals(item.getC1()))
                        .map(item -> {
                            InOutDayLog inOutDayLog = new InOutDayLog();
                            inOutDayLog.setType(InOutType.getByValue(item.getC3()));
                            inOutDayLog.setAddress(item.getC4());
                            inOutDayLog.setTime(LocalTime.parse(item.getC2(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
                            return inOutDayLog;
                        })
                        .sorted(Comparator.comparing(InOutDayLog::getTime))
                        .collect(Collectors.toList())
                );
                dayLog.setWorkedInPlan(LocalTime.of(8, 0, 0));
                dayLog.setWorkedInFact(calcFactWorked(dayLog.getInOutDayLogs()));
                return dayLog;
            })
            .collect(Collectors.toList());
    }

    private LocalTime calcFactWorked(List<InOutDayLog> inOutDayLogs) {
        List<InOutDayLog> lunchLogs = inOutDayLogs
            .stream()
            .filter(log -> log.getTime().isBefore(END_LUNCH_RANGE) && log.getTime().isAfter(START_LUNCH_RANGE))
            .collect(Collectors.toList());
        InOutDayLog startRange = new InOutDayLog();
        if (!lunchLogs.isEmpty() && lunchLogs.get(0).getType() == InOutType.IN) {
            startRange.setTime(START_LUNCH_RANGE);
            startRange.setType(InOutType.OUT);
            lunchLogs.add(0, startRange);
        } else {
            startRange.setTime(START_LUNCH_RANGE);
            startRange.setType(InOutType.IN);
            lunchLogs.add(0, startRange);
        }

        InOutDayLog endRange = new InOutDayLog();
        if (!lunchLogs.isEmpty() && lunchLogs.get(lunchLogs.size() - 1).getType() == InOutType.IN) {
            endRange.setTime(END_LUNCH_RANGE);
            endRange.setType(InOutType.OUT);
            lunchLogs.add(endRange);
        } else {
            endRange.setTime(END_LUNCH_RANGE);
            endRange.setType(InOutType.IN);
            lunchLogs.add(endRange);
        }

        LocalTime lunchAbsence = calcDifference(lunchLogs, InOutType.OUT, InOutType.IN);
        LocalTime presence = calcDifference(inOutDayLogs, InOutType.IN, InOutType.OUT);

        if (lunchAbsence.compareTo(MIDNIGHT) == 0) {
            if (presence.compareTo(LocalTime.of(1, 1)) > 0) {
                return presence.minusHours(1);
            } else {
                return LocalTime.of(0, 0);
            }
        } else if (lunchAbsence.compareTo(LUNCH_DURATION) >= 0) {
            return presence;
        } else {
            LocalTime subtractDiff = LUNCH_DURATION
                .minusHours(lunchAbsence.getHour())
                .minusMinutes(lunchAbsence.getMinute())
                .minusSeconds(lunchAbsence.getSecond());
            return presence
                .minusHours(subtractDiff.getHour())
                .minusMinutes(subtractDiff.getMinute())
                .minusSeconds(subtractDiff.getSecond());
        }
    }

    private LocalTime calcDifference(List<InOutDayLog> logs, InOutType type1, InOutType type2) {
        LocalTime temp = null;
        LocalTime difference = MIDNIGHT;
        for (InOutDayLog log : logs) {
            if (log.getType() == type1 && temp == null) {
                temp = log.getTime();
                continue;
            }
            if (log.getType() == type2 && temp != null) {
                LocalTime diff = log
                    .getTime()
                    .minusHours(temp.getHour())
                    .minusMinutes(temp.getMinute())
                    .minusSeconds(temp.getSecond());
                difference = difference
                    .plusHours(diff.getHour())
                    .plusMinutes(diff.getMinute())
                    .plusSeconds(diff.getSecond());
                temp = null;
            }
        }

        return difference;
    }

}
