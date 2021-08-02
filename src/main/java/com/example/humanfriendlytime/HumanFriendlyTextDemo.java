package com.example.humanfriendlytime;

import com.example.humanfriendlytime.util.TimeUtility;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Author Madhuri Mahesh
 */
public class HumanFriendlyTextDemo {

//      1:00 One o'clock
//      2:00 Two o'clock
//      13:00 One o'clock
//      13:05 Five past one
//      13:10 Ten past one
//      13:25 Twenty five past one
//      13:30 Half past one
//      13:35 Twenty five to two
//      13:55 Five to two
    public static void main(String[] args) {

        System.out.println("Local time now: " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")));

        String timeString;
        LocalTime finalTime = null;
        if(args.length !=0) {
            timeString =  args[0];
            finalTime= TimeUtility.getFinalTime(timeString);
        } else {
            finalTime = LocalTime.now();
        }

        String textHour = TimeUtility.convertIntToString(finalTime.getHour());
        String finalResult = TimeUtility.getHumanFriendlyTimeText(finalTime, textHour);
        System.out.println(finalResult);
    }
}
