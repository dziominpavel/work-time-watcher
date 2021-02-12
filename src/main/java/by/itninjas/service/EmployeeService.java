package by.itninjas.service;

import by.itninjas.dto.EmployeeDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeService {

    List<EmployeeDto> getAll();

    EmployeeDto getById(int userId);
}
