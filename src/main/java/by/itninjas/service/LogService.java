package by.itninjas.service;

import by.itninjas.dto.DayLogDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface LogService {

    List<DayLogDto> getAllByEmployeeId(int userId);

}
