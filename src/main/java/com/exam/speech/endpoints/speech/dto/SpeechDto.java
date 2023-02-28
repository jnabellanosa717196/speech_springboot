package com.exam.speech.endpoints.speech.dto;

import com.exam.speech.endpoints.author.dto.AuthorDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SpeechDto {
    private int id;
    private String content;
    private String keywords;
    private Date speechDate;
    private Date insertDate;
    private Date updateDate;
    private AuthorDto authorDto;
}
