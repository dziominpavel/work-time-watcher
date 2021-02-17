package by.itninjas.converter;

import static lombok.AccessLevel.PRIVATE;

import by.itninjas.domain.entity.Employee;
import by.itninjas.dto.EmployeeDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class EmployeeConverter {

    public static EmployeeDto toEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(String.valueOf(employee.getId()));
        employeeDto.setName(employee.getName());
        return employeeDto;
    }
}
