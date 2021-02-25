package by.itninjas.converter;

import by.itninjas.domain.entity.DayLog;
import by.itninjas.domain.entity.Employee;
import by.itninjas.domain.entity.InOutDayLog;
import by.itninjas.domain.enums.InOutType;
import by.itninjas.domain.xml.Row;
import by.itninjas.domain.xml.RowCollection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class XmlConverter {

    public Employee convertToEmployee(RowCollection rowCollection) {

        Employee employee = new Employee();
        List<Row> items = rowCollection.getRow();
        employee.setName(items.get(0).getC0());

        List<DayLog> dayLogDtoList = items
            .stream()
            .map(Row::getC1)
            .distinct()
            .sorted()
            .map(dateAsString -> {
                LocalDate date = LocalDate.parse(dateAsString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                DayLog dayLog = new DayLog();
                dayLog.setDate(date);
                dayLog.setInOutDayLogs(
                    items
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
                dayLog.setWorkedInFact(calcFactWorked(dayLog.getInOutDayLogs()));
                return dayLog;
            })
            .collect(Collectors.toList());
        employee.setDayLogs(dayLogDtoList);

        return employee;
    }

    private LocalTime calcFactWorked(List<InOutDayLog> inOutDayLogs) {
        List<InOutDayLog> lunchLogs = inOutDayLogs
            .stream()
            .filter(log -> log.getTime().isBefore(LocalTime.of(15, 0, 0)) && log.getTime().isAfter(LocalTime.of(12, 0, 0)))
            .collect(Collectors.toList());
        InOutDayLog startRange = new InOutDayLog();
        if (!lunchLogs.isEmpty() && lunchLogs.get(0).getType() == InOutType.IN) {
            startRange.setTime(LocalTime.of(12, 0, 0));
            startRange.setType(InOutType.OUT);
            lunchLogs.add(0, startRange);
        } else {
            startRange.setTime(LocalTime.of(12, 0, 0));
            startRange.setType(InOutType.IN);
            lunchLogs.add(0, startRange);
        }

        InOutDayLog endRange = new InOutDayLog();
        if (!lunchLogs.isEmpty() && lunchLogs.get(lunchLogs.size() - 1).getType() == InOutType.IN) {
            endRange.setTime(LocalTime.of(15, 0, 0));
            endRange.setType(InOutType.OUT);
            lunchLogs.add(endRange);
        } else {
            endRange.setTime(LocalTime.of(15, 0, 0));
            endRange.setType(InOutType.IN);
            lunchLogs.add(endRange);
        }

        LocalTime lunchAbsence = calcDifference(lunchLogs, InOutType.OUT, InOutType.IN);
        LocalTime presence = calcDifference(inOutDayLogs, InOutType.IN, InOutType.OUT);

        if (lunchAbsence.compareTo(LocalTime.of(0, 0, 0)) == 0) {
            return presence.minusHours(1);
        } else if (lunchAbsence.compareTo(LocalTime.of(1, 0, 0)) >= 0) {
            return presence;
        } else {
            LocalTime subtractDiff = LocalTime
                .of(1, 0, 0)
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
        LocalTime difference = LocalTime.of(0, 0, 0);
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
