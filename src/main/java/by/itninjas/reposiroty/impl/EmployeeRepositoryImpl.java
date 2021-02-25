package by.itninjas.reposiroty.impl;

import by.itninjas.domain.entity.Employee;
import by.itninjas.reposiroty.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final Map<Integer, Employee> STORAGE = new ConcurrentHashMap<>();

    @Override
    public void save(Employee employee) {
        Employee employeeWithSuchName = STORAGE
            .values()
            .stream()
            .filter(u -> u.getName().equals(employee.getName()))
            .findFirst()
            .orElse(null);

        if (employeeWithSuchName != null) {
            employee.setId(employeeWithSuchName.getId());
            STORAGE.put(employeeWithSuchName.getId(), employee);
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
    public Optional<Employee> getById(int id) {
        Employee searched = STORAGE.getOrDefault(id, null);
        return searched == null ? Optional.empty() : Optional.of(searched);
    }
}
