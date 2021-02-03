package by.itninjas.converter;

import by.itninjas.dto.DayInfoDtoUI;
import by.itninjas.dto.InOutInfoDtoUI;
import by.itninjas.dto.InOutType;
import by.itninjas.dto.UserDtoUI;
import by.itninjas.entity.R;
import by.itninjas.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class EntityToDtoConverter {

    public UserDtoUI convert(User user) {

        UserDtoUI userDtoUI = new UserDtoUI();
        userDtoUI.setName(user.getName());

        Map<String, List<R>> userDayInformation = user.getUserDayInformation();
        Set<String> dates = userDayInformation.keySet();

        for (String date : dates) {
            List<DayInfoDtoUI> dayInfoDtoUIList = new ArrayList<>();
            for (R r : userDayInformation.get(date)) {
                DayInfoDtoUI dayInfoDtoUI = new DayInfoDtoUI();
                InOutInfoDtoUI inOutInfoDto = new InOutInfoDtoUI();
                inOutInfoDto.setTime(r.getC2());
                inOutInfoDto.setInOutType(InOutType.getByValue(r.getC3()));
                inOutInfoDto.setAddress(r.getC4());
                dayInfoDtoUI.setInOutInfoDtoUI(inOutInfoDto);
                dayInfoDtoUIList.add(dayInfoDtoUI);
            }
            userDtoUI.addDayInfo(date, dayInfoDtoUIList);
        }

        return userDtoUI;
    }

}
