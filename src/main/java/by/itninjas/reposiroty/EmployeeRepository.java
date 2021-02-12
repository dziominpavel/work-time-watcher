package by.itninjas.reposiroty;

import by.itninjas.domain.entity.Employee;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository {

    void save(Employee employee);

    List<Employee> getAll();

    Employee getById(int id);
}
