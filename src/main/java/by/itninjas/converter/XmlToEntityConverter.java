package by.itninjas.converter;

import by.itninjas.entity.User;
import by.itninjas.jaxb.R;
import by.itninjas.jaxb.XmlEntity;

public class XmlToEntityConverter {

    public User convert(XmlEntity xmlEntity) {

        User user = new User();

        for (R r : xmlEntity.getR()) {
            user.setName(r.getC0());
            user.getUserDayInfo().entrySet (r.getC1());
            user.setName(r.getC2());
            user.setName(r.getC3());
            user.setName(r.getC4());

        }



    }
}
