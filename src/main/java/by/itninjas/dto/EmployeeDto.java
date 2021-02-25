package by.itninjas.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private LocalTime timeRequired;

    private LocalTime timeWorkedInFact;

}
