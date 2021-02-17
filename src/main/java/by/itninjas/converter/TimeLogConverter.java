package by.itninjas.converter;

import static lombok.AccessLevel.PRIVATE;

import by.itninjas.domain.entity.DayLog;
import by.itninjas.domain.entity.InOutDayLog;
import by.itninjas.dto.DayLogDto;
import by.itninjas.dto.InOutDayLogDto;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class TimeLogConverter {

    public static DayLogDto toDayLogDto(DayLog dayLog) {
        DayLogDto dayLogDto = new DayLogDto();
        dayLogDto.setDate(dayLog.getDate());
        dayLogDto.setTimeRequired(dayLog.getWorkedInPlan());
        dayLogDto.setTimeWorkedInFact(dayLog.getWorkedInFact());
        dayLogDto.setInOutDayLogs(dayLog.getInOutDayLogs().stream().map(TimeLogConverter::toInOutDayLogDto).collect(Collectors.toList()));
        return dayLogDto;
    }

    private static InOutDayLogDto toInOutDayLogDto(InOutDayLog inOutDayLog) {
        InOutDayLogDto inOutDayLogDto = new InOutDayLogDto();
        inOutDayLogDto.setType(inOutDayLog.getType());
        inOutDayLogDto.setTime(inOutDayLog.getTime());
        inOutDayLogDto.setAddress(inOutDayLog.getAddress());
        return inOutDayLogDto;
    }
}
