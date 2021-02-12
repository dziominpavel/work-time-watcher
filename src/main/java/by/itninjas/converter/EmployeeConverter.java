package by.itninjas.converter;

import by.itninjas.domain.entity.Employee;
import by.itninjas.dto.EmployeeDto;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {

    public EmployeeDto fromEntityToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(String.valueOf(employee.getId()));
        employeeDto.setName(employee.getName());
        return employeeDto;
    }

}
