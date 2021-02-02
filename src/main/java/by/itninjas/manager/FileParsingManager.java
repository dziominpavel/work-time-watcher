package by.itninjas.manager;

import by.itninjas.converter.EntityToDtoConverter;
import by.itninjas.converter.XmlReader;
import by.itninjas.converter.XmlToEntityConverter;
import by.itninjas.dto.MainDtoUI;
import by.itninjas.jaxb.XmlEntity;
import by.itninjas.reposiroty.XmlEntityRepository;
import java.util.ArrayList;
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


    public ArrayList<MainDtoUI> getFileInfo(MultipartFile[] files) {
        ArrayList<MainDtoUI> dtoList = new ArrayList<>();

        for (MultipartFile file : files) {
            XmlEntity xmlEntity = xmlReader.parse(file);
            localRepository.save(xmlEntity);
        }

        ArrayList<XmlEntity> allEntity = localRepository.getAll();
//        Map<Map<String, List<R>>, List<XmlEntity>> collected = allEntity.stream()
//            .collect(groupingBy(entity -> entity.getR().stream()
//                .collect(groupingBy(name -> name.getC0()))));

        for (XmlEntity xmlEntity : allEntity) {
            xmlToEntityConverter.convert(xmlEntity);
        }

        for (XmlEntity xmlEntity : allEntity) {

            dtoList.add();
        }

        return dtoList;

    }
}
