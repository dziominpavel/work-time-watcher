package by.itninjas.controller;

import by.itninjas.converter.TimeLogConverter;
import by.itninjas.dto.DayLogDto;
import by.itninjas.service.TimeLogService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/time-log")
public class TimeLogController {

    private final TimeLogService timeLogService;

    @Autowired
    public TimeLogController(TimeLogService timeLogService) {
        this.timeLogService = timeLogService;
    }

    @GetMapping("/{employeeId}")
    public List<DayLogDto> getEmployeeLogs(
        @PathVariable(name = "employeeId") int employeeId,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "30") int size) {

        return timeLogService
            .getAllByEmployeeId(employeeId, PageRequest.of(page - 1, size))
            .stream()
            .map(TimeLogConverter::toDayLogDto)
            .collect(Collectors.toList());
    }

    @PostMapping("/upload")
    public void uploadXml(@RequestParam("file") MultipartFile file) {
        timeLogService.uploadXml(file);
    }
}
