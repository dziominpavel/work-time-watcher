package by.itninjas.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RS")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlEntity {

    @XmlElement(name = "R")
    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof XmlEntity)) {
            return false;
        }
        XmlEntity xmlEntity = (XmlEntity) o;
        return items.equals(xmlEntity.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}