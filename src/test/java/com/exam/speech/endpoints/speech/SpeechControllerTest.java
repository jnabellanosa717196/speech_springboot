package com.exam.speech.endpoints.speech;

import com.exam.speech.endpoints.speech.service.SpeechService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class SpeechControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SpeechController controller;

    @Mock
    private SpeechService service;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @After
    public void tearDown(){
        mockMvc = null;
    }

    @Test
    public void test_deleteSpeech() throws Exception{
        String url = "/speech/delete/1";

        Mockito.when(service.deleteSpeech(Mockito.anyInt())).thenReturn(true);

        mockMvc.perform(post(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Speech successful delete.")));
    }
}
