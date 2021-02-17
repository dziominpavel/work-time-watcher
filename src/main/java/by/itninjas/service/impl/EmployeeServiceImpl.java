package by.itninjas.service.impl;

import by.itninjas.domain.entity.DayLog;
import by.itninjas.domain.entity.Employee;
import by.itninjas.reposiroty.EmployeeRepository;
import by.itninjas.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    @Override
    public Employee getById(int employeeId) {
        return employeeRepository
            .getById(employeeId)
            .orElseThrow(() -> new RuntimeException("no such employee"));
    }

    @Override
    public void create(String name, List<DayLog> dayLogs) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setDayLogs(dayLogs);
        employeeRepository.save(employee);
    }
}
