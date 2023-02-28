package com.exam.speech.endpoints.author;

import com.exam.speech.endpoints.author.dto.AuthorDto;
import com.exam.speech.endpoints.author.service.AuthorService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AuthorController controller;

    @Mock
    private AuthorService service;

    private AuthorDto authorDto;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();

        authorDto = new AuthorDto();
        authorDto.setFirstName("John");
        authorDto.setLastName("Doe");
        authorDto.setAddress("Babylon");
    }

    @After
    public void tearDown(){
        authorDto = null;
        mockMvc = null;
    }

    @Test
    public void test_getAllAuthors() throws Exception{
        String url = "/author/info/1";

         Mockito.when(service.getAuthorInformation(Mockito.anyInt())).thenReturn(authorDto);

         mockMvc.perform(get(url))
                 .andExpect(status().isOk())
                 .andExpect(content().string(containsString("Babylon")));
    }

}
