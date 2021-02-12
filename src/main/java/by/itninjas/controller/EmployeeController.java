package by.itninjas.controller;

import by.itninjas.dto.UserDto;
import by.itninjas.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private UserService userService;

    @GetMapping("/{employeeId}")
    public UserDto getUser(@PathVariable(name = "employeeId") int employeeId) {
        return userService.getById(employeeId);
    }

    @GetMapping
    public List<UserDto> getUser() {
        return userService.getAll();
    }

}
