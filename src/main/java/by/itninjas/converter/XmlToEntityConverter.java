package by.itninjas.converter;

import static java.util.stream.Collectors.groupingBy;

import by.itninjas.entity.User;
import by.itninjas.entity.R;
import by.itninjas.entity.XmlEntity;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class XmlToEntityConverter {

    public User convert(XmlEntity xmlEntity) {

        User user = new User();
        user.setName(xmlEntity.getR().get(0).getC0());

        Map<String, List<R>> collected = xmlEntity.getR().stream()
            .collect(groupingBy(R::getC1));

        LinkedHashMap<String, List<R>> sorted = collected.entrySet().stream()
            .sorted(Entry.comparingByKey())
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        user.setUserDayInformation(sorted);

        return user;

    }
}
