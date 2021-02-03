package by.itninjas.dto.ui;

import by.itninjas.entity.enums.InOutType;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InOutDayLogDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private InOutType type;

    private LocalDateTime datetime;

    private String address;
}
