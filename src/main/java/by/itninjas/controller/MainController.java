package by.itninjas.controller;

import by.itninjas.manager.FileParsingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
public class MainController {

    @Autowired
    private FileParsingManager fileParsingManager;

    @PostMapping("/parse")
    public void parse(@RequestParam("file") MultipartFile file) {
        fileParsingManager.parse(file);


    }

}
