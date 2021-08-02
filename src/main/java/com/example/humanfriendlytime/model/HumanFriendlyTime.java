package com.example.humanfriendlytime.model;

import lombok.Data;

@Data
public class HumanFriendlyTime {
    private String time;

    public HumanFriendlyTime(String time) {
        this.time = time;
    }
}
