package by.itninjas.reposiroty;

import by.itninjas.domain.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    void save(Employee employee);

    List<Employee> getAll();

    Optional<Employee> getById(int id);
}
