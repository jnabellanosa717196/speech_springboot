package com.exam.speech.endpoints.author.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDto {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
}
