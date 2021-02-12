package by.itninjas.service.impl;

import by.itninjas.converter.EmployeeConverter;
import by.itninjas.dto.EmployeeDto;
import by.itninjas.reposiroty.EmployeeRepository;
import by.itninjas.service.EmployeeService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements EmployeeService {

    @Autowired
    EmployeeConverter employeeConverter;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.getAll().stream().map(employeeConverter::fromEntityToDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getById(int userId) {
        return employeeConverter.fromEntityToDto(employeeRepository.getById(userId));
    }
}
