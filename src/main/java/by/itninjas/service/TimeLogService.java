package by.itninjas.service;

import by.itninjas.domain.entity.DayLog;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface TimeLogService {

    List<DayLog> getAllByEmployeeId(int employeeId);

    void uploadXml(MultipartFile file);

}
