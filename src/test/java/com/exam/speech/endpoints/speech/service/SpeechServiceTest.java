package com.exam.speech.endpoints.speech.service;

import com.exam.speech.database.persistence.entity.Author;
import com.exam.speech.database.persistence.entity.Speech;
import com.exam.speech.database.persistence.repository.AuthorRepository;
import com.exam.speech.database.persistence.repository.SpeechRepository;
import com.exam.speech.endpoints.author.service.AuthorService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SpeechServiceTest {

    @InjectMocks
    private SpeechService service;

    @Mock
    private SpeechRepository speechRepository;

    private static Speech speech;
    private static Author author;

    @BeforeClass
    public static void setUp() throws ParseException {
        author = new Author();
        author.setFirstName("Christian");
        author.setLastName("Bale");
        author.setAddress("Amsterdam");

        speech = new Speech();
        speech.setKeywords("90's");
        speech.setContent("90's theme or feels");
        speech.setSpeechDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-01"));
        speech.setAuthor(author);
    }

    @AfterClass
    public static void tearDown() {
        author = null;
        speech = null;
    }

    @Test
    public void test_getAllSpeech() {
        List<Speech> speechList = new ArrayList<>();
        speechList.add(speech);

        Mockito.when(speechRepository.findAll()).thenReturn(speechList);

        assertEquals(service.getAllSpeech().get(0).getContent(), "90's theme or feels");
        Mockito.verify(speechRepository, Mockito.times(1)).findAll();
    }
}
