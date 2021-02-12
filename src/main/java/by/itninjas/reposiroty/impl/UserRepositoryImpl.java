package by.itninjas.reposiroty.impl;

import by.itninjas.domain.entity.User;
import by.itninjas.reposiroty.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<Integer, User> STORAGE = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        User userWithSuchName = STORAGE
            .values()
            .stream()
            .filter(u -> u.getName().equals(user.getName()))
            .findFirst()
            .orElse(null);

        if (userWithSuchName != null) {
            STORAGE.put(userWithSuchName.getId(), userWithSuchName);
        } else {
            int maxId = STORAGE
                .keySet()
                .stream()
                .max(Integer::compareTo)
                .orElse(0);
            int newId = maxId + 1;
            user.setId(newId);
            STORAGE.put(newId, user);
        }
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(STORAGE.values());
    }

    @Override
    public User getById(int id) {
        return STORAGE.values().stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow(RuntimeException::new);
    }
}
