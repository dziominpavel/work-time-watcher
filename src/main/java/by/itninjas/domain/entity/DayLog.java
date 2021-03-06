package by.itninjas.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private LocalDate date;

    private LocalTime workedInPlan;

    private LocalTime workedInFact;

    private List<InOutDayLog> inOutDayLogs;


}
