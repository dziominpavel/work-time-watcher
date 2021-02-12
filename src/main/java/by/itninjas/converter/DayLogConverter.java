package by.itninjas.converter;

import by.itninjas.domain.entity.DayLog;
import by.itninjas.domain.entity.InOutDayLog;
import by.itninjas.dto.DayLogDto;
import by.itninjas.dto.InOutDayLogDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class DayLogConverter {


    public DayLogDto convertDaylogo(DayLog dayLog) {
        DayLogDto dayLogDto = new DayLogDto();
        dayLogDto.setDate(dayLog.getDate());
        dayLogDto.setTimeRequired(dayLog.getWorkedInPlan());
        dayLogDto.setTimeWorkedInFact(dayLog.getWorkedInFact());
        dayLogDto.setInOutDayLogs(dayLog.getInOutDayLogs().stream().map(this::convertInOutDayLog).collect(Collectors.toList()));
        return dayLogDto;
    }

    private InOutDayLogDto convertInOutDayLog(InOutDayLog inOutDayLog) {
        InOutDayLogDto inOutDayLogDto = new InOutDayLogDto();
        inOutDayLogDto.setType(inOutDayLog.getType());
        inOutDayLogDto.setTime(inOutDayLog.getTime());
        inOutDayLogDto.setAddress(inOutDayLog.getAddress());
        return inOutDayLogDto;
    }
}
