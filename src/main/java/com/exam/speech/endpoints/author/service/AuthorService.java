package com.exam.speech.endpoints.author.service;

import com.exam.speech.endpoints.author.dto.AuthorDto;
import com.exam.speech.database.persistence.entity.Author;
import com.exam.speech.endpoints.author.mapper.AuthorMapper;
import com.exam.speech.database.persistence.repository.AuthorRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer of Author endpoint.
 *
 * @author Joshua Abellanosa
 */
@Service
public class AuthorService {

    private final AuthorRepository authorRepo;

    @Autowired
    public AuthorService(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    public List<AuthorDto> getAllAuthors() {
        return AuthorMapper.INSTANCE.authorToAuthorDtoList(authorRepo.findAll());
    }

    public AuthorDto getAuthorInformation(int id) {
        Author authorInfo = authorRepo.findById(id);

        return AuthorMapper.INSTANCE.authorToAuthorDto(authorInfo);
    }

    @Transactional
    public AuthorDto saveAuthorInformation(AuthorDto toSaveAuthor) {
        Author newAuthor = AuthorMapper.INSTANCE.authorDtoToAuthor(toSaveAuthor);

        return AuthorMapper.INSTANCE.authorToAuthorDto(authorRepo.save(newAuthor));
    }
}
