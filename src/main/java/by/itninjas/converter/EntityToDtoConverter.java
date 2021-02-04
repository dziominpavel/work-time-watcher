package by.itninjas.converter;

import by.itninjas.dto.ui.DayLogDto;
import by.itninjas.dto.ui.InOutDayLogDto;
import by.itninjas.dto.ui.UserDto;
import by.itninjas.entity.Item;
import by.itninjas.entity.XmlEntity;
import by.itninjas.entity.enums.InOutType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class EntityToDtoConverter {

    public UserDto convert(XmlEntity xmlEntity) {

        UserDto userDto = new UserDto();
        List<Item> items = xmlEntity.getItems();
        userDto.setName(items.get(0).getC0());

        List<DayLogDto> dayLogDtoList = items
                                            .stream()
                                            .map(Item::getC1)
                                            .distinct()
                                            .sorted()
                                            .map(dateAsString -> {
                                                LocalDate date = LocalDate.parse(dateAsString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                                DayLogDto dayLog = new DayLogDto();
                                                dayLog.setDate(date);
                                                dayLog.setInOutDayLogs(
                                                    items
                                                        .stream()
                                                        .filter(item -> dateAsString.equals(item.getC1()))
                                                        .map(item -> {
                                                            InOutDayLogDto inOutDayLog = new InOutDayLogDto();
                                                            inOutDayLog.setType(InOutType.getByValue(item.getC3()));
                                                            inOutDayLog.setAddress(item.getC4());
                                                            inOutDayLog.setTime(LocalTime.parse(item.getC2(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
                                                            return inOutDayLog;
                                                        })
                                                        .sorted(Comparator.comparing(InOutDayLogDto::getTime))
                                                        .collect(Collectors.toList())
                                                );
                                                dayLog.setWorkedInFact(calcFactWorked(dayLog.getInOutDayLogs()));
                                                return dayLog;
                                            })
                                            .collect(Collectors.toList());
        userDto.setDayLogs(dayLogDtoList);

//        for (Item item : items) {
//
//            DayLogDto dayLogDto = new DayLogDto();
//
//            InOutDayLogDto inOutDayLogDto = new InOutDayLogDto();
//            inOutDayLogDto.setType(InOutType.getByValue(item.getC3()));
//            String dateTime = item.getC2();
//            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
//            inOutDayLogDto.setTime(localDateTime);
//            inOutDayLogDto.setAddress(item.getC4());
//
//            dayLogDto.getInOutDayLogs().add(inOutDayLogDto);
//
//            String date = item.getC1();
//            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//            dayLogDto.setDate(localDate);
//
//            userDto.getDayLogs().add(dayLogDto);
//
//        }

        return userDto;
    }

    private LocalTime calcFactWorked(List<InOutDayLogDto> inOutDayLogs) {
        List<InOutDayLogDto> lunchLogs = inOutDayLogs
            .stream()
            .filter(log -> log.getTime().isBefore(LocalTime.of(15, 0, 0)) && log.getTime().isAfter(LocalTime.of(12, 0, 0)))
            .collect(Collectors.toList());
        if (!lunchLogs.isEmpty() && lunchLogs.get(0).getType() == InOutType.IN) {
            InOutDayLogDto startRange = new InOutDayLogDto();
            startRange.setTime(LocalTime.of(12, 0, 0));
            startRange.setType(InOutType.OUT);
            lunchLogs.add(0, startRange);
        } else {
            InOutDayLogDto startRange = new InOutDayLogDto();
            startRange.setTime(LocalTime.of(12, 0, 0));
            startRange.setType(InOutType.IN);
            lunchLogs.add(0, startRange);
        }

        if (!lunchLogs.isEmpty() && lunchLogs.get(lunchLogs.size() - 1).getType() == InOutType.IN) {
            InOutDayLogDto endRange = new InOutDayLogDto();
            endRange.setTime(LocalTime.of(15, 0, 0));
            endRange.setType(InOutType.OUT);
            lunchLogs.add(endRange);
        } else {
            InOutDayLogDto endRange = new InOutDayLogDto();
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

    private LocalTime calcDifference(List<InOutDayLogDto> logs, InOutType type1, InOutType type2) {
        LocalTime temp = null;
        LocalTime difference = LocalTime.of(0, 0, 0);
        for (InOutDayLogDto log : logs) {
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
