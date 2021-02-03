package by.itninjas.controller;

import by.itninjas.dto.ui.UserDto;
import by.itninjas.service.FileParsingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MainController {

    @Autowired
    private FileParsingService fileParsingManager;

    @PostMapping("/parse")
    public List<UserDto> parse(@RequestParam("files") MultipartFile[] files) {

        return fileParsingManager.getFileInfo(files);
    }
}
