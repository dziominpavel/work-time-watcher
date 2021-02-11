package by.itninjas.dto;

import by.itninjas.domain.enums.InOutType;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InOutDayLogDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private InOutType type;

    private LocalTime time;

    private String address;
}
