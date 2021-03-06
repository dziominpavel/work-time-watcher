package by.itninjas.domain.xml;

import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;

//@XmlRootElement(name = "R")
//@XmlAccessorType(XmlAccessType.PROPERTY)
public class Row {

    @XmlElement
    private String C0;

    @XmlElement
    private String C1;

    @XmlElement
    private String C2;

    @XmlElement
    private String C3;

    @XmlElement
    private String C4;

    public String getC3() {
        return C3;
    }

    public void setC3(String C3) {
        this.C3 = C3;
    }

    public String getC4() {
        return C4;
    }

    public void setC4(String C4) {
        this.C4 = C4;
    }

    public String getC0() {
        return C0;
    }

    public void setC0(String C0) {
        this.C0 = C0;
    }

    public String getC1() {
        return C1;
    }

    public void setC1(String C1) {
        this.C1 = C1;
    }

    public String getC2() {
        return C2;
    }

    public void setC2(String C2) {
        this.C2 = C2;
    }

    @Override
    public String toString() {
        return "ClassPojo [C3 = " + C3 + ", C4 = " + C4 + ", C0 = " + C0 + ", C1 = " + C1 + ", C2 = " + C2 + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Row)) {
            return false;
        }
        Row item = (Row) o;
        return C0.equals(item.C0) && C1.equals(item.C1) && C2.equals(item.C2) && C3.equals(item.C3) && C4.equals(item.C4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(C0, C1, C2, C3, C4);
    }
}
