package com.exam.speech.endpoints.speech;

import com.exam.speech.endpoints.speech.dto.SpeechDto;
import com.exam.speech.endpoints.speech.service.SpeechService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Speech API endpoint.
 *
 * @author Joshua Abellanosa
 */
@RestController
@RequestMapping("/speech")
public class SpeechController {

    private final SpeechService speechService;

    @Autowired
    public SpeechController(SpeechService speechService) {
        this.speechService = speechService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllSpeech() {
        List<SpeechDto> allSpeech = speechService.getAllSpeech();

        if (CollectionUtils.isEmpty(allSpeech)) {
            return new ResponseEntity<>("No saved speeches.", HttpStatus.OK);
        }

        return new ResponseEntity<>(allSpeech, HttpStatus.OK);
    }

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public ResponseEntity<?> searchSpeechByAuthor(@RequestParam(name = "authorName") String authorName) {
        List<SpeechDto> speechList = speechService.searchSpeechByAuthor(authorName);

        if (CollectionUtils.isEmpty(speechList)) {
            return new ResponseEntity<>("No saved speeches with author : " + authorName, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(speechList, HttpStatus.OK);
    }

    @RequestMapping(value = "/search/content", method = RequestMethod.GET)
    public ResponseEntity<?> searchSpeechByContent(@RequestParam(name = "content") String content) {
        List<SpeechDto> speechList = speechService.searchSpeechByContent(content);

        if (CollectionUtils.isEmpty(speechList)) {
            return new ResponseEntity<>("No saved speeches with content : " + content, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(speechList, HttpStatus.OK);
    }

    @RequestMapping(value = "/search/keyword", method = RequestMethod.GET)
    public ResponseEntity<?> searchSpeechByKeyword(@RequestParam(name = "keyword") String keyword) {
        List<SpeechDto> speechList = speechService.searchSpeechByKeyword(keyword);

        if (CollectionUtils.isEmpty(speechList)) {
            return new ResponseEntity<>("No saved speeches with keyword : " + keyword, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(speechList, HttpStatus.OK);
    }

    @RequestMapping(value = "/search/date", method = RequestMethod.GET)
    public ResponseEntity<?> searchSpeechByDate(@RequestParam(name = "startDate") Date startDate,
                                                @RequestParam(name = "endDate") Date endDate) {
        List<SpeechDto> speechList = speechService.searchSpeechByDate(startDate, endDate);

        if (CollectionUtils.isEmpty(speechList)) {
            return new ResponseEntity<>("No saved speeches within given date range.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(speechList, HttpStatus.OK);
    }

    /**
     * Searches the word that meets the criteria in any of fields.
     *
     * @param word Search word
     * @return List of speech
     */
    @RequestMapping(value = "/search/{word}", method = RequestMethod.GET)
    public ResponseEntity<?> allSearch(@PathVariable("word") String word) {
        List<SpeechDto> speechList = speechService.allSearch(word);

        if (CollectionUtils.isEmpty(speechList)) {
            return new ResponseEntity<>("No saved speeches containing info : " + word, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(speechList, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveNewSpeech(@Valid @RequestBody SpeechDto speechDto) {
        SpeechDto savedSpeech = speechService.saveNewSpeech(speechDto);

        if (ObjectUtils.isEmpty(savedSpeech)) {
            return new ResponseEntity<>("Speech unsuccessful save.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(savedSpeech, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateSpeech(@Valid @RequestBody SpeechDto speechDto) {
        boolean success = speechService.updateSpeech(speechDto);

        if (!success) {
            return new ResponseEntity<>("Speech unsuccessful update.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Speech successful update.", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteSpeech(@PathVariable("id") int id) {
        boolean success = speechService.deleteSpeech(id);

        if (!success) {
            return new ResponseEntity<>("Speech unsuccessful delete.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Speech successful delete.", HttpStatus.OK);
        }
    }
}
