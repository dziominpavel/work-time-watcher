package by.itninjas.service.impl;

import by.itninjas.converter.DayLogConverter;
import by.itninjas.domain.entity.DayLog;
import by.itninjas.domain.entity.User;
import by.itninjas.dto.DayLogDto;
import by.itninjas.reposiroty.UserRepository;
import by.itninjas.service.LogService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogServiceImpl implements LogService {

    @Autowired
    DayLogConverter dayLogConverter;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<DayLogDto> getAllByUserId(int userId) {
        return userRepository.getById(userId).getDayLogs().stream().map(dayLogConverter::convertDaylogo).collect(Collectors.toList());
    }
}
