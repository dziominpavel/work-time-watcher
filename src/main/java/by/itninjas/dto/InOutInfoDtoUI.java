package by.itninjas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InOutInfoDtoUI {

    private InOutType inOutType;

    private String time;
}
