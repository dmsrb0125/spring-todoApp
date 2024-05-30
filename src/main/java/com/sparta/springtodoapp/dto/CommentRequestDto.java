package com.sparta.springtodoapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String content; // 댓글 내용
    private Long userId; // 사용자 ID

}