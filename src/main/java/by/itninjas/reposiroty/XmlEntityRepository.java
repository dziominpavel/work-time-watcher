package by.itninjas.reposiroty;

import by.itninjas.entity.XmlEntity;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public interface XmlEntityRepository {

    void save(XmlEntity xmlEntity);

    ArrayList<XmlEntity> getAll();

}
