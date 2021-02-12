package by.itninjas.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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

    private LocalTime timeRequired = LocalTime.of(8, 0, 0);

    private LocalTime timeWorkedInFact;

    private List<InOutDayLogDto> inOutDayLogs = new LinkedList<>();



}
