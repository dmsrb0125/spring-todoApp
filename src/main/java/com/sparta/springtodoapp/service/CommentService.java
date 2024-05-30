package com.sparta.springtodoapp.service;

import com.sparta.springtodoapp.dto.CommentRequestDto;
import com.sparta.springtodoapp.entity.Comment;
import com.sparta.springtodoapp.entity.Todo;
import com.sparta.springtodoapp.repository.CommentRepository;
import com.sparta.springtodoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

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
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        Todo todo = optionalTodo.orElseThrow(() -> new IllegalArgumentException("해당 할일Id는 존재하지않습니다."));

        String content = requestDto.getContent();
        Long userId = requestDto.getUserId();

        Comment comment = new Comment(todo, content, userId);
        return commentRepository.save(comment);
    }

    // 댓글 조회
    public List<Comment> getComments(Long todoId) {
        return commentRepository.findByTodoId(todoId);
    }


}