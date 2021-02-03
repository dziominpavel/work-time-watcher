package by.itninjas.converter;

import by.itninjas.dto.ui.DayLogDto;
import by.itninjas.dto.ui.InOutDayLogDto;
import by.itninjas.dto.ui.UserDto;
import by.itninjas.entity.Item;
import by.itninjas.entity.XmlEntity;
import by.itninjas.entity.enums.InOutType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class EntityToDtoConverter {

    public UserDto convert(XmlEntity xmlEntity) {

        UserDto userDto = new UserDto();
        List<Item> items = xmlEntity.getItems();
        userDto.setName(items.get(0).getC0());

        for (Item item : items) {

            DayLogDto dayLogDto = new DayLogDto();

            InOutDayLogDto inOutDayLogDto = new InOutDayLogDto();
            inOutDayLogDto.setType(InOutType.getByValue(item.getC3()));
            String dateTime = item.getC2();
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            inOutDayLogDto.setDatetime(localDateTime);
            inOutDayLogDto.setAddress(item.getC4());

            dayLogDto.getInOutDayLogs().add(inOutDayLogDto);

            String date = item.getC1();
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            dayLogDto.setDate(localDate);

            userDto.getDayLogs().add(dayLogDto);

        }

        return userDto;
    }

}
