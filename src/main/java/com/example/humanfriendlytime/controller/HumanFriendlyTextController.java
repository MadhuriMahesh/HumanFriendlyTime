package com.example.humanfriendlytime.controller;

import com.example.humanfriendlytime.model.HumanFriendlyTime;
import com.example.humanfriendlytime.service.HumanFriendlyTextService;
import com.example.humanfriendlytime.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Author Madhuri Mahesh
 */
@RestController
public class HumanFriendlyTextController {

    @Autowired
    private HumanFriendlyTextService humanFriendlyTextService;

    /**
     * Endpoint give text time for given string time
     * @param time
     * @return HumanFriendlyTime with text time
     */
    @GetMapping("/{time}")
    public HumanFriendlyTime getHumanFriendlyTime(@PathVariable String time) throws ResourceNotFoundException {
        if( time == null & !isValidTime(time)){
            throw new ResourceNotFoundException("wrong time format");
        }
        return humanFriendlyTextService.convertToHumanFriendlyTimeText(time);
    }

    /**
     * Endpoint give text time for current time
     * @return HumanFriendlyTime with text time
     */
    @GetMapping("/")
    public HumanFriendlyTime getHumanFriendlyTime() {
        return humanFriendlyTextService.convertToHumanFriendlyTimeText();
    }

    protected boolean isValidTime(String time) {
        try {
            LocalTime.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
