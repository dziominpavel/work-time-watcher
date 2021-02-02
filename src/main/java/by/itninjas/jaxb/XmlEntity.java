package by.itninjas.jaxb;

import java.util.ArrayList;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RS")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class XmlEntity {

    @XmlElement
    private ArrayList<R> R = new ArrayList<>();

    public ArrayList<R> getR() {
        return R;
    }

    public void setR(ArrayList<R> r) {
        R = r;
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
        return R.equals(xmlEntity.R);
    }

    @Override
    public int hashCode() {
        return Objects.hash(R);
    }
}