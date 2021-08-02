package com.example.humanfriendlytime.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class TimeUtilityTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getFinalTime() {
        //execute
        LocalTime time1 = TimeUtility.getFinalTime("13:30");
        System.out.println(time1);

        //verify
        assertEquals("01:30", time1.toString());

        //execute
        LocalTime time2 = TimeUtility.getFinalTime("01:30");
        System.out.println(time2);

        //verify
        assertEquals("01:30", time2.toString());
    }

    @Test
    public void parseTime() {
        //execute
        String parseTime1 = TimeUtility.parseTime("13:00 PM");
        System.out.println(parseTime1);

        //verify
        assertEquals("01:00", parseTime1);

        //execute
        String parseTime2 = TimeUtility.parseTime("01:00 AM");
        System.out.println(parseTime2);

        //verify
        assertEquals("01:00", parseTime2);
    }

    @Test
    public void parseTimeWithException() {
        expectedException.expect(DateTimeParseException.class);
        expectedException.expectMessage("Text '13:00 AM' could not be parsed: Conflict found: Field AmPmOfDay 1 differs from AmPmOfDay 0 derived from 13:00");

        //execute
        String parseTime = TimeUtility.parseTime("13:00 AM");
        System.out.println(parseTime);
    }

    @Test
    public void parseTimeWithException2() {
        expectedException.expect(DateTimeParseException.class);
        expectedException.expectMessage("Text '01:00 PM' could not be parsed: Conflict found: Field AmPmOfDay 0 differs from AmPmOfDay 1 derived from 01:00");

        //execute
        String parseTime = TimeUtility.parseTime("01:00 PM");
        System.out.println(parseTime);
    }



    @Test
    public void convertIntToString() {
        //execute
        String intToString1 = TimeUtility.convertIntToString(25);

        //verify
        assertEquals("Twenty Five", intToString1);


        //execute
        String intToString2 = TimeUtility.convertIntToString(-59);

        //verify
        assertEquals("Minus Fifty Nine", intToString2);

        //execute
        String intToString3 = TimeUtility.convertIntToString(101);

        //verify
        assertNull(intToString3);

        //execute
        String intToString4 = TimeUtility.convertIntToString(15);

        //verify
        assertEquals("Fifteen", intToString4);

    }

    @Test
    public void getHumanFriendlyTimeTextWithOclock() {
        // set up
        LocalTime localTime = LocalTime.of(1,0);
        String textHour = "One";

        //execute
        String humanFriendlyTimeText = TimeUtility.getHumanFriendlyTimeText(localTime, textHour);

        //verify
        assertEquals("One o' clock", humanFriendlyTimeText);
    }

    @Test
    public void getHumanFriendlyTimeTextWithHalfHour() {
        // set up
        LocalTime localTime = LocalTime.of(1,30);
        String textHour = "One";

        //execute
        String humanFriendlyTimeText = TimeUtility.getHumanFriendlyTimeText(localTime, textHour);

        //verify
        assertEquals("Half past One", humanFriendlyTimeText);
    }

    @Test
    public void getHumanFriendlyTimeTextWithPastHour() {
        // set up
        LocalTime localTime = LocalTime.of(1,15);
        String textHour = "One";

        //execute
        String humanFriendlyTimeText = TimeUtility.getHumanFriendlyTimeText(localTime, textHour);

        //verify
        assertEquals("Fifteen past One", humanFriendlyTimeText);
    }

    @Test
    public void getHumanFriendlyTimeTextWithToHour() {
        // set up
        LocalTime localTime = LocalTime.of(1,35);
        String textHour = "One";

        //execute
        String humanFriendlyTimeText = TimeUtility.getHumanFriendlyTimeText(localTime, textHour);

        //verify
        assertEquals("Twenty Five to Two", humanFriendlyTimeText);
    }



}