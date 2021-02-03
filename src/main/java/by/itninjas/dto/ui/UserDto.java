package by.itninjas.dto.ui;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;

    private LinkedList<DayLogDto> dayLogs = new LinkedList<>();

}
