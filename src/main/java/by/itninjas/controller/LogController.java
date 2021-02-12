package by.itninjas.controller;

import by.itninjas.dto.DayLogDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    //todo
    @GetMapping("/log/{userId}")
    public List<DayLogDto> getUserLogs(@PathVariable(name = "userId") int userId) {
        return new ArrayList<>();
    }


}
