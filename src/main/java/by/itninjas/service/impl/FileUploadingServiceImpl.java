package by.itninjas.service.impl;

import by.itninjas.converter.XmlConverter;
import by.itninjas.domain.entity.User;
import by.itninjas.domain.xml.RowCollection;
import by.itninjas.reposiroty.UserRepository;
import by.itninjas.service.FileUploadingService;
import by.itninjas.util.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadingServiceImpl implements FileUploadingService {

    @Autowired
    private XmlReader xmlReader;

    @Autowired
    private XmlConverter xmlConverter;

    @Autowired
    @Qualifier("userRepositoryImpl")
    private UserRepository userRepository;

    @Override
    public void upload(MultipartFile file) {

        RowCollection rowCollection = xmlReader.parse(file);
        User user = xmlConverter.convertToUser(rowCollection);
        userRepository.save(user);

    }
}
