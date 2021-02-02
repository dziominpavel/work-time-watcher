package by.itninjas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DayInfoDtoUI {

    private static final long serialVersionUID = 1L;

    private String date;

    private String needTimeToWork;

    private String factTime;

    private String firstEnter;

    private String lastExit;

    private InOutInfoDtoUI inOutInfoDtoUI;
}
