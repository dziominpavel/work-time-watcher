package by.itninjas.controller;

import by.itninjas.service.impl.FileUploadingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private FileUploadingServiceImpl fileUploadingService;

    @PostMapping("/upload")
    public void parse(@RequestParam("file") MultipartFile file) {
        fileUploadingService.upload(file);
    }
}
