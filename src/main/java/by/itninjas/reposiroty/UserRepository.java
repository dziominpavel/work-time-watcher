package by.itninjas.reposiroty;

import by.itninjas.domain.entity.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    void save(User user);

    List<User> getAll();
}
