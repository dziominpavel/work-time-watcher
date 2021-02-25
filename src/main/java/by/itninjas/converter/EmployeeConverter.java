package by.itninjas.converter;

import static lombok.AccessLevel.PRIVATE;

import by.itninjas.domain.entity.DayLog;
import by.itninjas.domain.entity.Employee;
import by.itninjas.dto.EmployeeDto;
import by.itninjas.dto.TimeDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class EmployeeConverter {

    public static EmployeeDto toEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(String.valueOf(employee.getId()));
        employeeDto.setName(employee.getName());

        int totalSeconds = 0;

        for (DayLog dayLog : employee.getDayLogs()) {
            int seconds = dayLog.getWorkedInPlan().getHour() * 60 * 60
                + dayLog.getWorkedInPlan().getMinute() * 60
                + dayLog.getWorkedInPlan().getSecond();
            totalSeconds = totalSeconds + seconds;
        }
        employeeDto.setTimeRequired(secondsToTimeDto(totalSeconds));

        totalSeconds = 0;
        for (DayLog dayLog : employee.getDayLogs()) {
            int seconds = dayLog.getWorkedInFact().getHour() * 60 * 60
                + dayLog.getWorkedInFact().getMinute() * 60
                + dayLog.getWorkedInFact().getSecond();
            totalSeconds = totalSeconds + seconds;
        }
        employeeDto.setTimeWorkedInFact(secondsToTimeDto(totalSeconds));

        return employeeDto;
    }

    private static TimeDto secondsToTimeDto(int seconds) {
        TimeDto timedto = new TimeDto();
        timedto.setHour(seconds / 3600);
        timedto.setMinute((seconds % 3600) / 60);
        timedto.setSecond(seconds % 60);
        return timedto;
    }

}
