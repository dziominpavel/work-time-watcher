package by.itninjas.dto.ui;

import by.itninjas.entity.enums.InOutType;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
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
