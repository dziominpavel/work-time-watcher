package by.itninjas.controller;

import by.itninjas.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {


    @GetMapping("/parse")
    public UserDto parse(@RequestParam("files") MultipartFile[] files) {

        return new UserDto();
    }

}
