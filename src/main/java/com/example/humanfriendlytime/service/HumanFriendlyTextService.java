package com.example.humanfriendlytime.service;

import com.example.humanfriendlytime.model.HumanFriendlyTime;
import com.example.humanfriendlytime.util.TimeUtility;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Author Madhuri Mahesh
 */
@Service
public class HumanFriendlyTextService {

    /**
     * Method give text time for current time
     * @param timeString
     * @return HumanFriendlyTime with text time
     */
    public HumanFriendlyTime convertToHumanFriendlyTimeText(String timeString) {
        LocalTime finalTime= TimeUtility.getFinalTime(timeString);
        String textHour = TimeUtility.convertIntToString(finalTime.getHour());
        String finalResult = TimeUtility.getHumanFriendlyTimeText(finalTime, textHour);
        return new HumanFriendlyTime(finalResult);
    }

    /**
     * Method give text time for given string time
     * @return HumanFriendlyTime with text time
     */
    public HumanFriendlyTime convertToHumanFriendlyTimeText() {
        LocalTime finalTime = LocalTime.parse(LocalTime.now().format( DateTimeFormatter.ofPattern("hh:mm")));
        String textHour = TimeUtility.convertIntToString(finalTime.getHour());
        String finalResult = TimeUtility.getHumanFriendlyTimeText(finalTime, textHour);
        return new HumanFriendlyTime(finalResult);
    }

}
