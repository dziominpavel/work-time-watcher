package by.itninjas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDtoUI {

    private static final long serialVersionUID = 1L;

    private String name;

    private Map<String, List<DayInfoDtoUI>> daysInfo = new LinkedHashMap<>();

    public Map<String, List<DayInfoDtoUI>> addDayInfo(String date, List<DayInfoDtoUI> dayInfo) {
        daysInfo.put(date, dayInfo);
        return daysInfo;
    }


}
