package com.exam.speech.endpoints.author.service;

import com.exam.speech.database.persistence.entity.Author;
import com.exam.speech.database.persistence.repository.AuthorRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {
    @InjectMocks
    private AuthorService service;

    @Mock
    private AuthorRepository authorRepository;

    private static Author author;

    @BeforeClass
    public static void setUp() {
        author = new Author();
        author.setFirstName("Christian");
        author.setLastName("Bale");
        author.setAddress("Amsterdam");
    }

    @AfterClass
    public static void tearDown() {
        author = null;
    }

    @Test
    public void test_getAllAuthors() {
        List<Author> authorList = new ArrayList<>();
        authorList.add(author);

        Mockito.when(authorRepository.findAll()).thenReturn(authorList);

        assertEquals(service.getAllAuthors().get(0).getAddress(), "Amsterdam");
        Mockito.verify(authorRepository, Mockito.times(1)).findAll();
    }
}
