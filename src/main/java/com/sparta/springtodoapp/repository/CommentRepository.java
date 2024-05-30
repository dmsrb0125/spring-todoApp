package com.sparta.springtodoapp.repository;

import com.sparta.springtodoapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTodoId(Long todoId);
}