package by.itninjas.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private String name;

    private Map<String, List<R>> userDayInformation = new HashMap<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, List<R>> getUserDayInformation() {
        return userDayInformation;
    }

    public void setUserDayInformation(Map<String, List<R>> userDayInformation) {
        this.userDayInformation = userDayInformation;
    }
}
