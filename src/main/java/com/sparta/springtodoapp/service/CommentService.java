package com.sparta.springtodoapp.service;

import com.sparta.springtodoapp.dto.CommentRequestDto;
import com.sparta.springtodoapp.entity.Comment;
import com.sparta.springtodoapp.entity.Todo;
import com.sparta.springtodoapp.entity.User;
import com.sparta.springtodoapp.repository.CommentRepository;
import com.sparta.springtodoapp.repository.TodoRepository;
import com.sparta.springtodoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, TodoRepository todoRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    // 댓글 생성
    public Comment createComment(Long todoId, CommentRequestDto requestDto) {

        // 선택한 일정의 ID를 입력 받지 않은 경우 예외 처리
        if (todoId == null) {
            throw new IllegalArgumentException("할 일 ID를 입력 해주세요.");
        }

        Optional<Todo> optionalTodo = todoRepository.findById(todoId);

        // 유효하지 않은 할 일 ID인 경우 예외 처리
        Todo todo = optionalTodo.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 할 일 ID입니다"));

        // 댓글 내용이 비어 있는 경우 예외 처리
        String content = requestDto.getContent();
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("댓글을 입력 해주세요.");
        }

        Optional<User> userOptional = userRepository.findById(requestDto.getUserId());
        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 유저 ID입니다"));

        Comment comment = new Comment(todo, content, user);
        return commentRepository.save(comment);
    }

    // 댓글 조회
    public List<Comment> getComments(Long todoId) {
        return commentRepository.findByTodoId(todoId);
    }

    // 댓글 수정
    public Optional<Comment> updateComment(Long todoId, Long commentId, CommentRequestDto requestDto) {

        // 할 일 Id 입력 받지 못한경우 예외처리
        if (todoId == null) {
            throw new IllegalArgumentException("할 일 ID가 null일 수 없습니다");
        }

        // 댓글 Id 입력 받지 못한경우 예외처리
        if (commentId == null) {
            throw new IllegalArgumentException("댓글 ID가 null일 수 없습니다");
        }

        return commentRepository.findById(commentId).map(comment -> {
            String content = requestDto.getContent();

            // 매핑된 데이터 아니면 예외처리
            if (!comment.getTodo().getId().equals(todoId)) {
                throw new IllegalArgumentException("해당 댓글은 지정된 할 일에 속하지 않습니다");
            }

            // 선택한 댓글의 사용자가 현재 사용자와 일치하지 않은 경우 예외처리
            Optional<User> userOptional = userRepository.findById(requestDto.getUserId());
            User user = userOptional.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 유저 ID입니다"));
            if (!user.equals(comment.getUser())) {
                throw new IllegalArgumentException("댓글 작성자가 아닙니다");
            }

            // 댓글 입력이 없을시 예외처리
            if (content == null || content.trim().isEmpty()) {
                throw new IllegalArgumentException("댓글 내용을 입력해주세요");
            }

            comment.setContent(content);
            return commentRepository.save(comment);
        });
    }

    // 댓글 삭제
    public void deleteComment(Long todoId, Long commentId) {
        // 할 일 Id 입력 받지 못한경우 예외처리
        if (todoId == null) {
            throw new IllegalArgumentException("할 일 ID를 입력 해주세요.");
        }

        // 댓글 Id 입력 받지 못한경우 예외처리
        if (commentId == null) {
            throw new IllegalArgumentException("댓글 ID를 입력 해주세요.");
        }

        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Comment comment = commentOptional.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 댓글 ID입니다: " + commentId));

        // 매핑된 데이터 아니면 예외처리
        if (!comment.getTodo().getId().equals(todoId)) {
            throw new IllegalArgumentException("해당 댓글은 지정된 할 일에 속하지 않습니다");
        }

        commentRepository.deleteById(commentId);
    }
}
