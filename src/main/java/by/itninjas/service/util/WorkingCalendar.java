package by.itninjas.service.util;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class WorkingCalendar {

    private static final String hoursInDayByDefault = "08:00:00";
    private static final String hoursInDayBeforeHoliday = "07:00:00";

    private static final Map<String, String> dates = Map.of(
        "2021-01-06", hoursInDayBeforeHoliday,
        "2021-04-30", hoursInDayBeforeHoliday,
        "2021-07-02", hoursInDayBeforeHoliday,
        "2021-12-31", hoursInDayBeforeHoliday);

    public static LocalTime getTimeByDate(String dateAsString) {
        return LocalTime.parse(dates.getOrDefault(dateAsString, hoursInDayByDefault), DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
