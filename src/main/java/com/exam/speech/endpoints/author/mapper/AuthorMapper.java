package com.exam.speech.endpoints.author.mapper;

import com.exam.speech.endpoints.author.dto.AuthorDto;
import com.exam.speech.database.persistence.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Author mapper from DTO to entity and vice versa.
 *
 * @author Joshua Abellanosa
 */
@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    Author authorDtoToAuthor(AuthorDto authorDto);

    AuthorDto authorToAuthorDto(Author author);

    List<AuthorDto> authorToAuthorDtoList(List<Author> authorList);
}
