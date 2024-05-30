package com.sparta.springtodoapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoRequestDto {
    private String title; // 할일 제목
    private String description; // 할일 내용
}
