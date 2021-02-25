package by.itninjas.controller;

import by.itninjas.converter.EmployeeConverter;
import by.itninjas.dto.EmployeeDto;
import by.itninjas.service.EmployeeService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public EmployeeDto getEmployee(@PathVariable(name = "employeeId") int employeeId) {
        return EmployeeConverter.toEmployeeDto(employeeService.getById(employeeId));
    }

    @GetMapping
    public List<EmployeeDto> getEmployees(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "30") int size) {
        return employeeService
            .getAll(PageRequest.of(page - 1, size))
            .stream()
            .map(EmployeeConverter::toEmployeeDto)
            .collect(Collectors.toList());
    }

}
