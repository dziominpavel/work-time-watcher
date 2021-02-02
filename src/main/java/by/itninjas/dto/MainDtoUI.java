package by.itninjas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MainDtoUI {

    private static final long serialVersionUID = 1L;

    private List<UserDtoUI> userDtoUIList;

}
