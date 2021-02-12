package by.itninjas.service.impl;

import by.itninjas.converter.UserConverter;
import by.itninjas.dto.UserDto;
import by.itninjas.reposiroty.UserRepository;
import by.itninjas.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserConverter userConverter;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDto> getAll() {
        return userRepository.getAll().stream().map(userConverter::fromEntityToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getById(int userId) {
        return userConverter.fromEntityToDto(userRepository.getById(userId));
    }
}
