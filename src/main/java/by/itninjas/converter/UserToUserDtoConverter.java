package by.itninjas.converter;

import by.itninjas.domain.entity.DayLog;
import by.itninjas.domain.entity.InOutDayLog;
import by.itninjas.domain.entity.User;
import by.itninjas.dto.DayLogDto;
import by.itninjas.dto.InOutDayLogDto;
import by.itninjas.dto.UserDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter {

    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(String.valueOf(user.getId()));
        userDto.setName(user.getName());
        userDto.setDayLogs(user.getDayLogs().stream().map(this::convertDaylogo).collect(Collectors.toList()));
        return userDto;
    }

    private DayLogDto convertDaylogo(DayLog dayLog) {
        DayLogDto dayLogDto = new DayLogDto();
        dayLogDto.setDate(dayLog.getDate());
        dayLogDto.setWorkedInPlan(dayLog.getWorkedInPlan());
        dayLogDto.setWorkedInFact(dayLog.getWorkedInFact());
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
