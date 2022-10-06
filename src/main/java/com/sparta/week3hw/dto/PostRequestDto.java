package com.sparta.week3hw.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    private String username;
    private String title;
    private String password;
    private String contents;

}
