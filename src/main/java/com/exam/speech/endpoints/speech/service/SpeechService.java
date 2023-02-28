package com.exam.speech.endpoints.speech.service;

import com.exam.speech.endpoints.author.dto.AuthorDto;
import com.exam.speech.endpoints.author.mapper.AuthorMapper;
import com.exam.speech.endpoints.speech.dto.SpeechDto;
import com.exam.speech.database.persistence.entity.Author;
import com.exam.speech.database.persistence.entity.Speech;
import com.exam.speech.endpoints.speech.mapper.SpeechMapper;
import com.exam.speech.database.persistence.repository.AuthorRepository;
import com.exam.speech.database.persistence.repository.SpeechRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Service layer of Speech endpoint.
 *
 * @author Joshua Abellanosa
 */
@Service
public class SpeechService {

    private final SpeechRepository speechRepo;
    private final AuthorRepository authorRepo;

    @Autowired
    public SpeechService(SpeechRepository speechRepo, AuthorRepository authorRepo) {
        this.speechRepo = speechRepo;
        this.authorRepo = authorRepo;
    }

    public List<SpeechDto> getAllSpeech() {
        List<Speech> speechList = speechRepo.findAll();

        return SpeechMapper.INSTANCE.speechToSpeechDtoList(speechList);
    }

    public List<SpeechDto> searchSpeechByAuthor(String authorName) {
        List<Author> authorList = authorRepo.findAuthorByFirstNameOrLastName(authorName);
        List<Speech> speechesByAuthor = speechRepo.findByAuthorIn(authorList);

        return SpeechMapper.INSTANCE.speechToSpeechDtoList(speechesByAuthor);
    }

    public List<SpeechDto> searchSpeechByContent(String content) {
        List<Speech> speechesByContent = speechRepo.findByContentLike(content);

        return SpeechMapper.INSTANCE.speechToSpeechDtoList(speechesByContent);
    }

    public List<SpeechDto> searchSpeechByKeyword(String keyword) {
        List<Speech> speechesByKeyword = speechRepo.findByKeywordsLike(keyword);

        return SpeechMapper.INSTANCE.speechToSpeechDtoList(speechesByKeyword);
    }

    public List<SpeechDto> searchSpeechByDate(Date startDate, Date endDate) {
        List<Speech> speechesByDate = speechRepo.findBySpeechDateBetween(startDate, endDate);

        return SpeechMapper.INSTANCE.speechToSpeechDtoList(speechesByDate);
    }

    public List<SpeechDto> allSearch(String search) {
        List<Speech> speechesBySearch = speechRepo.findByGenericSearch(search);

        return SpeechMapper.INSTANCE.speechToSpeechDtoList(speechesBySearch);
    }

    @Transactional
    public SpeechDto saveNewSpeech(SpeechDto toSaveSpeech) {
        Author author = AuthorMapper.INSTANCE.authorDtoToAuthor(toSaveSpeech.getAuthorDto());
        Speech newSpeech = SpeechMapper.INSTANCE.speechDtoToSpeech(toSaveSpeech);
        newSpeech.setInsertDate(new Date());
        newSpeech.setUpdateDate(new Timestamp(new Date().getTime()));
        newSpeech.setAuthor(author);

        Speech savedSpeech = speechRepo.save(newSpeech);
        Author savedAuthor = savedSpeech.getAuthor();

        SpeechDto speechDto = SpeechMapper.INSTANCE.speechToSpeechDto(savedSpeech);
        AuthorDto authorDto = AuthorMapper.INSTANCE.authorToAuthorDto(savedAuthor);
        speechDto.setAuthorDto(authorDto);

        return speechDto;
    }

    @Transactional
    public boolean updateSpeech(SpeechDto toUpdateSpeech) {
        Speech dbSpeech = speechRepo.findById(toUpdateSpeech.getId());

        if (ObjectUtils.isNotEmpty(dbSpeech)) {

            if (StringUtils.isNotEmpty(toUpdateSpeech.getContent())) {
                dbSpeech.setContent(toUpdateSpeech.getContent());
            }

            if (StringUtils.isNotEmpty(toUpdateSpeech.getKeywords())) {
                dbSpeech.setKeywords(toUpdateSpeech.getKeywords());
            }

            if (toUpdateSpeech.getSpeechDate() != null) {
                dbSpeech.setSpeechDate(toUpdateSpeech.getSpeechDate());
            }
            dbSpeech.setUpdateDate(new Timestamp(new Date().getTime()));

            speechRepo.save(dbSpeech);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteSpeech(int id) {
        Speech dbSpeech = speechRepo.findById(id);

        if (ObjectUtils.isNotEmpty(dbSpeech)) {
            speechRepo.delete(dbSpeech);
            return true;
        }
        return false;
    }
}
