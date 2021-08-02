package com.example.humanfriendlytime.util;

/**
 * Author Madhuri Mahesh
 */
public enum TimeUnit {

    AM("Am"),
    PM("Pm"),
    PAST("past"),
    TO("to"),
    HALF("Half"),
    O_CLOCK ("o' clock");

    private String text;

    TimeUnit(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

}
