package by.itninjas.reposiroty.impl;

import by.itninjas.reposiroty.XmlEntityRepository;
import by.itninjas.domain.xml.RowCollection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Repository;

@Repository
public class XmlEntityRepositoryImpl implements XmlEntityRepository {


    private Set<RowCollection> storage = new HashSet<>();

    public void save(RowCollection xmlEntity) {
        storage.add(xmlEntity);
    }

    @Override
    public ArrayList<RowCollection> getAll() {
        return new ArrayList<>(storage);
    }
}
