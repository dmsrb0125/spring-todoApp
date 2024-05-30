package com.sparta.springtodoapp.service;

import com.sparta.springtodoapp.dto.CommentRequestDto;
import com.sparta.springtodoapp.entity.Comment;
import com.sparta.springtodoapp.entity.Todo;
import com.sparta.springtodoapp.repository.CommentRepository;
import com.sparta.springtodoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, TodoRepository todoRepository) {
        this.commentRepository = commentRepository;
        this.todoRepository = todoRepository;
    }

    // 댓글 생성
    public Comment createComment(Long todoId, CommentRequestDto requestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new IllegalArgumentException("Invalid todo ID"));
        String content = requestDto.getContent();

        // 생성자를 사용하여 댓글 등록
        Comment comment = new Comment(todo, content);
        return commentRepository.save(comment);
    }

    // 댓글 조회
    public List<Comment> getComments(Long todoId) {
        return commentRepository.findByTodoId(todoId);
    }


}