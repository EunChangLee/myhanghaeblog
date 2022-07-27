package com.sparta.hanghaeblog;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String name;
    private String passward;
    private String content;
}