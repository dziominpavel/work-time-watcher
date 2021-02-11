package by.itninjas.reposiroty;

import by.itninjas.domain.xml.RowCollection;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public interface XmlEntityRepository {

    void save(RowCollection rowCollection);

    ArrayList<RowCollection> getAll();

}
