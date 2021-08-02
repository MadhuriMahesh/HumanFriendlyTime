package com.example.humanfriendlytime.service;

import com.example.humanfriendlytime.model.HumanFriendlyTime;
import com.example.humanfriendlytime.util.TimeUtility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalTime;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TimeUtility.class)
public class HumanFriendlyTextServiceTest {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void convertToHumanFriendlyTimeText() {
        //setup
        PowerMockito.mockStatic(TimeUtility.class);
        PowerMockito.when(TimeUtility.convertIntToString(anyInt())).thenReturn("One");
        PowerMockito.when(TimeUtility.getHumanFriendlyTimeText(any(), any())).thenReturn("One o' clock");
        HumanFriendlyTextService humanFriendlyTextService = new HumanFriendlyTextService();

        //execute
        HumanFriendlyTime humanFriendlyTime = humanFriendlyTextService.convertToHumanFriendlyTimeText();


        //verify
        assertEquals("One o' clock", humanFriendlyTime.getTime());
    }

    @Test
    public void testConvertToHumanFriendlyTimeText() {
        //setup
        PowerMockito.mockStatic(TimeUtility.class);
        PowerMockito.when(TimeUtility.getFinalTime(any())).thenReturn(LocalTime.of(13, 00));
        PowerMockito.when(TimeUtility.convertIntToString(anyInt())).thenReturn("One");
        PowerMockito.when(TimeUtility.getHumanFriendlyTimeText(any(), any())).thenReturn("One o' clock");
        HumanFriendlyTextService humanFriendlyTextService = new HumanFriendlyTextService();

        //execute
        HumanFriendlyTime humanFriendlyTime = humanFriendlyTextService.convertToHumanFriendlyTimeText("13:00");


        //verify
        assertEquals("One o' clock", humanFriendlyTime.getTime());
    }

}