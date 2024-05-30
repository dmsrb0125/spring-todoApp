package com.sparta.springtodoapp.controller;

import com.sparta.springtodoapp.dto.CommentRequestDto;
import com.sparta.springtodoapp.entity.Comment;
import com.sparta.springtodoapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos/{todoId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 생성 요청
    @PostMapping
    public Comment createComment(@PathVariable Long todoId, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(todoId, requestDto);
    }

    // 해당되는 댓글 전체 조회 요청
    @GetMapping
    public List<Comment> getComments(@PathVariable Long todoId) {
        return commentService.getComments(todoId);
    }

}