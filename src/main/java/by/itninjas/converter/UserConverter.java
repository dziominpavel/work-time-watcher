package by.itninjas.converter;

import by.itninjas.domain.entity.User;
import by.itninjas.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto fromEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(String.valueOf(user.getId()));
        userDto.setName(user.getName());
        return userDto;
    }

}
