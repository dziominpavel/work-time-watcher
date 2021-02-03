package by.itninjas.service;

import by.itninjas.converter.EntityToDtoConverter;
import by.itninjas.converter.XmlReader;
import by.itninjas.dto.ui.UserDto;
import by.itninjas.entity.XmlEntity;
import by.itninjas.reposiroty.XmlEntityRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileParsingService {

    @Autowired
    private XmlReader xmlReader;

    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    @Autowired
    @Qualifier("xmlEntityRepositoryImpl")
    private XmlEntityRepository localRepository;


    public List<UserDto> getFileInfo(MultipartFile[] files) {

        for (MultipartFile file : files) {
            XmlEntity xmlEntity = xmlReader.parse(file);
            localRepository.save(xmlEntity);
        }

        ArrayList<XmlEntity> allEntity = localRepository.getAll();

//        ArrayList<User> userList = new ArrayList<>();
//        for (XmlEntity xmlEntity : allEntity) {
//            User user = xmlToEntityConverter.convert(xmlEntity);
//            userList.add(user);
//        }

        ArrayList<UserDto> userDtoList = new ArrayList<>();
        for (XmlEntity xmlEntity : allEntity) {
            UserDto dto = entityToDtoConverter.convert(xmlEntity);
            userDtoList.add(dto);
        }

        return userDtoList;
    }
}
