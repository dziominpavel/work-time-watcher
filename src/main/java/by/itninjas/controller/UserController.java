package by.itninjas.controller;

import by.itninjas.dto.UserDto;
import by.itninjas.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public UserDto getUser(@PathVariable(name = "userId") int userId) {
        return userService.getById(userId);
    }

    @GetMapping("/user")
    public List<UserDto> getUser() {
        return userService.getAll();
    }

}
