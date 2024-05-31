package com.sparta.springtodoapp.service;

import com.sparta.springtodoapp.dto.TodoRequestDto;
import com.sparta.springtodoapp.entity.Todo;
import com.sparta.springtodoapp.entity.User;
import com.sparta.springtodoapp.repository.TodoRepository;
import com.sparta.springtodoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    // 할일 생성
    public Todo createTodo(TodoRequestDto todoRequestDto) {
        String title = todoRequestDto.getTitle();
        String description = todoRequestDto.getDescription();
        Long userId = todoRequestDto.getUserId();

        // 예외처리
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("할일 제목을 입력 해주세요");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("할일 내용을 입력 해주세요");
        }
        if (userId == null) {
            throw new IllegalArgumentException("생성자 id를 입력 해주세요.");
        }

        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 유저 ID입니다"));

        Todo todo = new Todo(title, description, user); // 생성자 사용
        return todoRepository.save(todo);
    }

    // 할일 전체 목록 조회
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    // 특정 할일 정보 조회
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }   // Optional를 사용하여 구현

    // 할일 수정
    public Optional<Todo> updateTodo(Long id, TodoRequestDto todoRequestDto) {
        return todoRepository.findById(id).map(todo -> {
            String title = todoRequestDto.getTitle();
            String description = todoRequestDto.getDescription();

            // 예외처리
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("할일 제목을 입력 해주세요");
            }
            if (description == null || description.trim().isEmpty()) {
                throw new IllegalArgumentException("할일 내용을 입력 해주세요");
            }

            // 세터사용
            todo.setTitle(title);
            todo.setDescription(description);
            return todoRepository.save(todo);
        }); // Optional를 사용하여 구현
    }

    // 할일 삭제
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
