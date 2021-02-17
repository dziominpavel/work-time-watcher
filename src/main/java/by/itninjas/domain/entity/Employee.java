package by.itninjas.domain.entity;

import java.util.Collections;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Employee {

    private Integer id;

    private String name;

    private List<DayLog> dayLogs;

}
