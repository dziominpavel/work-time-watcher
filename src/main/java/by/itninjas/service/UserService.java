package by.itninjas.service;

import by.itninjas.dto.UserDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    List<UserDto> getAll();

    UserDto getById(int userId);
}
