package by.itninjas.service.impl;

import by.itninjas.converter.DayLogConverter;
import by.itninjas.dto.DayLogDto;
import by.itninjas.reposiroty.EmployeeRepository;
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
    EmployeeRepository employeeRepository;

    @Override
    public List<DayLogDto> getAllByEmployeeId(int userId) {
        return employeeRepository.getById(userId).getDayLogs().stream().map(dayLogConverter::convertDaylogo).collect(Collectors.toList());
    }
}
