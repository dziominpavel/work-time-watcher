package by.itninjas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDtoUI {

    private static final long serialVersionUID = 1L;

    private String name;

    private DayInfoDtoUI dayInfo;


}
