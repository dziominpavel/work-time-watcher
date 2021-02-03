package by.itninjas.dto.ui;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayLogDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private LocalDate date;

    private LinkedList<InOutDayLogDto> inOutDayLogs = new LinkedList<>();

}
