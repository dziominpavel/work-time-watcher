package by.itninjas.converter;

import by.itninjas.dto.DayInfoDtoUI;
import by.itninjas.dto.MainDtoUI;
import by.itninjas.dto.UserDtoUI;
import by.itninjas.jaxb.R;
import by.itninjas.jaxb.XmlEntity;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class EntityToDtoConverter {

    public MainDtoUI convert(XmlEntity xmlEntity) {
        MainDtoUI mainDtoUI = new MainDtoUI();
        ArrayList<R> userInfo = xmlEntity.getR();


        ArrayList<UserDtoUI> userDtoUIList = new ArrayList<>();

        for (R element : userInfo) {
            UserDtoUI userDtoUI = new UserDtoUI();
            DayInfoDtoUI dayInfoDtoUI = new DayInfoDtoUI();

            userDtoUI.setName(element.getC0());

            dayInfoDtoUI.setDate(element.getC1());
            dayInfoDtoUI.setNeedTimeToWork(String.valueOf(8 * 60));
//            dayInfoDtoUI.setFactTime(String.valueOf(Integer.parseInt(element.getC2()) / 60));
//            dayInfoDtoUI.setFirstEnter(element.getC3());
//            dayInfoDtoUI.setLastExit(element.getC4());


            dayInfoDtoUI.setInOutInfoDtoUI(null);
            userDtoUI.setDayInfo(dayInfoDtoUI);

            userDtoUIList.add(userDtoUI);
        }

        mainDtoUI.setUserDtoUIList(userDtoUIList);

        return mainDtoUI;
    }

}
