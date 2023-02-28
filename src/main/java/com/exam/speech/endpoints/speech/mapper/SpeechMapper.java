package com.exam.speech.endpoints.speech.mapper;

import com.exam.speech.endpoints.speech.dto.SpeechDto;
import com.exam.speech.database.persistence.entity.Speech;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Speech mapper from DTO to entity and vice versa.
 *
 * @author Joshua Abellanosa
 */
@Mapper
public interface SpeechMapper {

    SpeechMapper INSTANCE = Mappers.getMapper(SpeechMapper.class);

    Speech speechDtoToSpeech(SpeechDto speechDto);

    SpeechDto speechToSpeechDto(Speech speech);

    List<SpeechDto> speechToSpeechDtoList(List<Speech> speechList);
}
