package by.itninjas.jaxb;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "R")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class R {

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
        if (!(o instanceof R)) {
            return false;
        }
        R r = (R) o;
        return C0.equals(r.C0) && C1.equals(r.C1) && C2.equals(r.C2) && C3.equals(r.C3) && C4.equals(r.C4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(C0, C1, C2, C3, C4);
    }
}
