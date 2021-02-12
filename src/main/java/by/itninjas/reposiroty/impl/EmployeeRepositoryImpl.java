package by.itninjas.reposiroty.impl;

import by.itninjas.domain.entity.Employee;
import by.itninjas.reposiroty.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final Map<Integer, Employee> STORAGE = new ConcurrentHashMap<>();

    @Override
    public void save(Employee employee) {
        Employee employeeWithSuchName = STORAGE
            .values()
            .stream()
            .filter(u -> u.getName().equals(employee.getName()))
            .findFirst()
            .orElse(null);

        if (employeeWithSuchName != null) {
            STORAGE.put(employeeWithSuchName.getId(), employeeWithSuchName);
        } else {
            int maxId = STORAGE
                .keySet()
                .stream()
                .max(Integer::compareTo)
                .orElse(0);
            int newId = maxId + 1;
            employee.setId(newId);
            STORAGE.put(newId, employee);
        }
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(STORAGE.values());
    }

    @Override
    public Employee getById(int id) {
        return STORAGE.values().stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow(RuntimeException::new);
    }
}
