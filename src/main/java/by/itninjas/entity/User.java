package by.itninjas.entity;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;

    private Map<String, UserDayInfo> userDayInfo = new HashMap<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, UserDayInfo> getUserDayInfo() {
        return userDayInfo;
    }

    public void setUserDayInfo(Map<String, UserDayInfo> userDayInfo) {
        this.userDayInfo = userDayInfo;
    }
}
