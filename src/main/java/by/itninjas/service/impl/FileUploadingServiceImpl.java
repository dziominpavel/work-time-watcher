package by.itninjas.service.impl;

import by.itninjas.converter.RowCollectionToUserConverter;
import by.itninjas.domain.entity.User;
import by.itninjas.domain.xml.RowCollection;
import by.itninjas.reposiroty.UserRepository;
import by.itninjas.service.FileUploadingService;
import by.itninjas.util.XmlReader;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadingServiceImpl implements FileUploadingService {

    @Autowired
    private XmlReader xmlReader;

    @Autowired
    private RowCollectionToUserConverter rowCollectionToUserConverter;

    @Autowired
    @Qualifier("userRepositoryImpl")
    private UserRepository userRepository;

    @Override
    public void upload(MultipartFile file) {

//        for (MultipartFile file : files) {
            RowCollection rowCollection = xmlReader.parse(file);
            User user = rowCollectionToUserConverter.convert(rowCollection);
            userRepository.save(user);
//        }


        List<User> userList = userRepository.getAll();
        System.out.println();

//        ArrayList<User> userList = new ArrayList<>();
//        for (RowCollection xmlEntity : userList1) {
//            userList.add(user);
//        }
//
//        ArrayList<UserDto> userDtoList = new ArrayList<>();
//        for (RowCollection xmlEntity : userList1) {
//            UserDto dto = entityToDtoConverter.convert(xmlEntity);
//            userDtoList.add(dto);
//        }

    }
}
