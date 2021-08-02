package com.example.humanfriendlytime.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * Author Madhuri Mahesh
 */
public class TimeUtility {

    private final static int MAX_MINUTE= 30;
    private final static int HOUR_MINUTE= 60;

    /**
     * Method calculate time before or after the noon
     *
     * @param timeString
     * @return
     */
    public static LocalTime getFinalTime(String timeString) {
        LocalTime time = LocalTime.parse(timeString);
        LocalTime finalTime;
        if (time.isAfter(LocalTime.NOON)) {
            timeString = timeString + " " + TimeUnit.PM;
            String parseTime = TimeUtility.parseTime(timeString);
            finalTime = LocalTime.parse(parseTime);
        } else {
            timeString = timeString + " " + TimeUnit.AM;
            String parseTime = TimeUtility.parseTime(timeString);
            finalTime = LocalTime.parse(parseTime);
        }
        return finalTime;
    }

    /**
     *
     * @param time String eg. "13:00 PM"
     * @return
     */
    public static String parseTime(String time) {
        String result =                                       // Text representing the value of our date-time object.
                LocalTime.parse(                                  // Class representing a time-of-day value without a date and without a time zone.
                        time ,                                  // Your `String` input text.
                        DateTimeFormatter.ofPattern(                  // Define a formatting pattern to match your input text.
                                "HH:mm a" ,
                                Locale.US                                 // `Locale` determines the human language and cultural norms used in localization. Needed here to translate the `AM` & `PM` value.
                        )                                             // Returns a `DateTimeFormatter` object.
                )                                                 // Return a `LocalTime` object.
                        .format( DateTimeFormatter.ofPattern("hh:mm") );

        System.out.println("time parse: " +  result);

        return result;
    }

    public static final String[] units = { "", "One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
            "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen" };

    public static final String[] tens = {
            "", 		// 0
            "",		// 1
            "Twenty", 	// 2
            "Thirty", 	// 3
            "Forty", 	// 4
            "Fifty", 	// 5
            "Sixty", 	// 6
            "Seventy",	// 7
            "Eighty", 	// 8
            "Ninety" 	// 9
    };


    /**
     * Converts numbers to words in numbers
     * @param n number
     * @return Number String
     */
    public static String convertIntToString(final int n) {
        if (n < 0) {
            return "Minus " + convertIntToString(-n);
        }

        if (n < 20) {
            return units[n];
        }

        if (n < 100) {
            return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
        }

        return null;
    }

    /**
     *  Time in Human Readable string
     *
     * @param finalTime
     * @param textHour
     * @return String HumanReadable String
     */
    public static  String getHumanFriendlyTimeText(LocalTime finalTime, String textHour) {
        String finalResult;
        if (finalTime.getMinute() == 0) {// o' clock
            finalResult = textHour + " " + TimeUnit.O_CLOCK.getText();
        } else if(finalTime.getMinute() == MAX_MINUTE) {// Half past an textHour
            finalResult = getConvertToText(TimeUnit.HALF.getText(), TimeUnit.PAST.getText(), textHour);
        } else if(finalTime.getMinute() < MAX_MINUTE){// past an textHour
            finalResult = getConvertToText(Objects.requireNonNull(TimeUtility.convertIntToString(finalTime.getMinute())), TimeUnit.PAST.getText(), textHour);
        } else {// to an Hour
            textHour = TimeUtility.convertIntToString(finalTime.plus(1, ChronoUnit.HOURS).getHour());
            int minutes = HOUR_MINUTE - finalTime.getMinute();
            finalResult = getConvertToText(Objects.requireNonNull(TimeUtility.convertIntToString(minutes)), TimeUnit.TO.getText(), textHour);
        }
        return finalResult;
    }

    private static String getConvertToText(String minutes, String timeUnit, String hour) {
        return minutes.concat(" " + timeUnit + " " + hour);
    }

}
