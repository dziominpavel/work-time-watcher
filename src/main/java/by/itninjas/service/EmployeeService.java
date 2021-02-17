package by.itninjas.service;

import by.itninjas.domain.entity.DayLog;
import by.itninjas.domain.entity.Employee;
import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Employee getById(int employeeId);

    void create(String name, List<DayLog> dayLogs);
}
