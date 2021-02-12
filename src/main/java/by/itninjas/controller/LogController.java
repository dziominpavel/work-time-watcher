package by.itninjas.controller;

import by.itninjas.dto.DayLogDto;
import by.itninjas.service.LogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/log/{userId}")
    public List<DayLogDto> getUserLogs(@PathVariable(name = "userId") int userId) {
        return logService.getAllByUserId(userId);
    }


}
