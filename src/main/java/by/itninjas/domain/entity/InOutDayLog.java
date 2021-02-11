package by.itninjas.domain.entity;

import by.itninjas.domain.enums.InOutType;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InOutDayLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private InOutType type;

    private LocalTime time;

    private String address;
}
