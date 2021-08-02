package com.example.humanfriendlytime.controller;


import com.example.humanfriendlytime.model.HumanFriendlyTime;
import com.example.humanfriendlytime.service.HumanFriendlyTextService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class HumanFriendlyTextControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HumanFriendlyTextService humanFriendlyTextService;

    @InjectMocks
    private HumanFriendlyTextController humanFriendlyTextController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.humanFriendlyTextController).build();
    }

    @Test
    public void getHumanFriendlyTime() throws Exception {
        //setup
        String timeString = "One o' claock";
        HumanFriendlyTime humanFriendlyTime = new HumanFriendlyTime(timeString);
        Mockito.when(humanFriendlyTextService.convertToHumanFriendlyTimeText()).thenReturn(humanFriendlyTime);


        //execute and verify
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("time").value(timeString))
                .andReturn();

    }

    @Test
    public void getHumanFriendlyTimeWithParam() throws Exception {
        //setup
        String timeString = "One o' claock";
        HumanFriendlyTime humanFriendlyTime = new HumanFriendlyTime(timeString);
        Mockito.when(humanFriendlyTextService.convertToHumanFriendlyTimeText(any())).thenReturn(humanFriendlyTime);


        //execute and verify
        this.mockMvc.perform(get("/" + "13:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("time").value(timeString))
                .andReturn();

    }

}