package com.exam.speech.endpoints.author;

import com.exam.speech.endpoints.author.dto.AuthorDto;
import com.exam.speech.endpoints.author.service.AuthorService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author API endpoint.
 *
 * @author Joshua Abellanosa
 */
@RestController
@RequestMapping(path = "/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAuthors() {
        List<AuthorDto> allAuthors = authorService.getAllAuthors();

        if (CollectionUtils.isEmpty(allAuthors)) {
            return new ResponseEntity<>("No saved authors.", HttpStatus.OK);
        }

        return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthorInformation(@PathVariable("id") int id) {
        AuthorDto authorInformation = authorService.getAuthorInformation(id);

        if (ObjectUtils.isEmpty(authorInformation)) {
            return new ResponseEntity<>("Author information not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(authorInformation, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveAuthorInformation(@RequestBody AuthorDto authorDto) {
        AuthorDto savedAuthor = authorService.saveAuthorInformation(authorDto);

        if (ObjectUtils.isEmpty(savedAuthor)) {
            return new ResponseEntity<>("Author information unsuccessful save.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(savedAuthor, HttpStatus.OK);
    }
}
