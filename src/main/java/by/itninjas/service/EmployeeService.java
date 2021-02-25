package by.itninjas.service;

import by.itninjas.domain.entity.DayLog;
import by.itninjas.domain.entity.Employee;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    List<Employee> getAll(Pageable pageable);

    Employee getById(int employeeId);

    void create(String name, List<DayLog> dayLogs);
}
