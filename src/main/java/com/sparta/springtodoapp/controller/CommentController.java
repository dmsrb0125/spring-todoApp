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

    @PostMapping
    public Comment createComment(@PathVariable Long todoId, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(todoId, requestDto);
    }

    @GetMapping
    public List<Comment> getComments(@PathVariable Long todoId) {
        return commentService.getComments(todoId);
    }


}