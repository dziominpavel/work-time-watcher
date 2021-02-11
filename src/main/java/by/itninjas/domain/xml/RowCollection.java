package by.itninjas.domain.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RS")
@XmlAccessorType(XmlAccessType.FIELD)
public class RowCollection {

    @XmlElement(name = "R")
    private List<Row> rowList = new ArrayList<>();

    public List<Row> getRow() {
        return rowList;
    }

    public void setRow(List<Row> items) {
        this.rowList = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RowCollection)) {
            return false;
        }
        RowCollection xmlEntity = (RowCollection) o;
        return rowList.equals(xmlEntity.rowList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowList);
    }
}