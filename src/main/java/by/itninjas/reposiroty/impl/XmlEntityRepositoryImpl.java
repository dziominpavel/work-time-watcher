package by.itninjas.reposiroty.impl;

import by.itninjas.reposiroty.XmlEntityRepository;
import by.itninjas.entity.XmlEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Repository;

@Repository
public class XmlEntityRepositoryImpl implements XmlEntityRepository {


    private Set<XmlEntity> storage = new HashSet<>();

    public void save(XmlEntity xmlEntity) {
        storage.add(xmlEntity);
    }

    @Override
    public ArrayList<XmlEntity> getAll() {
        return new ArrayList<>(storage);
    }
}
