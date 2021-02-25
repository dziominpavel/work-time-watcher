package by.itninjas.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int hour;

    private int minute;

    private int second;

}
