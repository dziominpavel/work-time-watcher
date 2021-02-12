package by.itninjas.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface FileUploadingService {

    void upload(MultipartFile file);
}
