package com.sparta.springtodoapp.controller;

import com.sparta.springtodoapp.dto.CommentRequestDto;
import com.sparta.springtodoapp.entity.Comment;
import com.sparta.springtodoapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Comment> createComment(@PathVariable Long todoId, @RequestBody CommentRequestDto requestDto) {
        try {
            Comment comment = commentService.createComment(todoId, requestDto);
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // 해당되는 댓글 전체 조회 요청
    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long todoId) {
        try {
            List<Comment> comments = commentService.getComments(todoId);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // 선택돤 댓글 수정 요청
    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long todoId, @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        try {
            Optional<Comment> updatedComment = commentService.updateComment(todoId, commentId, requestDto);
            return updatedComment.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // 선택한 댓글 삭제 요청
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long todoId, @PathVariable Long commentId) {
        try {
            commentService.deleteComment(todoId, commentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 예외를 처리할 메서드
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
