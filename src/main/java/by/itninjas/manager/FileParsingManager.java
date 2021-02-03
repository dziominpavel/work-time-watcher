package by.itninjas.manager;

import by.itninjas.converter.EntityToDtoConverter;
import by.itninjas.converter.XmlReader;
import by.itninjas.converter.XmlToEntityConverter;
import by.itninjas.dto.MainDtoUI;
import by.itninjas.dto.UserDtoUI;
import by.itninjas.entity.R;
import by.itninjas.entity.User;
import by.itninjas.entity.XmlEntity;
import by.itninjas.reposiroty.XmlEntityRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileParsingManager {

    @Autowired
    private XmlReader xmlReader;

    @Autowired
    private XmlToEntityConverter xmlToEntityConverter;
    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    @Autowired
    @Qualifier("xmlEntityRepositoryImpl")
    private XmlEntityRepository localRepository;


    public MainDtoUI getFileInfo(MultipartFile[] files) {

        for (MultipartFile file : files) {
            XmlEntity xmlEntity = xmlReader.parse(file);
            localRepository.save(xmlEntity);
        }

        ArrayList<XmlEntity> allEntity = localRepository.getAll();

        ArrayList<User> userList = new ArrayList<>();
        for (XmlEntity xmlEntity : allEntity) {
            User user = xmlToEntityConverter.convert(xmlEntity);
            userList.add(user);
        }

        ArrayList<UserDtoUI> dtoList = new ArrayList<>();
        for (User user : userList) {
            UserDtoUI dto = entityToDtoConverter.convert(user);
            dtoList.add(dto);
        }

        MainDtoUI mainDtoUI = new MainDtoUI();
        mainDtoUI.setUserDtoUIList(dtoList);

        return mainDtoUI;
    }
}
