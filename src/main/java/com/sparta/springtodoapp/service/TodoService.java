package com.sparta.springtodoapp.service;

import com.sparta.springtodoapp.dto.TodoRequestDto;
import com.sparta.springtodoapp.entity.Todo;
import com.sparta.springtodoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // 할일 생성
    public Todo createTodo(TodoRequestDto todoRequestDto) {
        String title = todoRequestDto.getTitle();
        String description = todoRequestDto.getDescription();
        Long userId = todoRequestDto.getUserId();

        // 생성자를 사용하여 할일 등록
        Todo todo = new Todo(title, description, userId);
        return todoRepository.save(todo);
    }

    // 할일 전체 목록 조회
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    // 할일 수정
    public Todo updateTodo(Long id, TodoRequestDto todoRequestDto) {
        // 해당 아이디 찾기
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 할 일이 존재하지 않습니다."));

        String title = todoRequestDto.getTitle();
        String description = todoRequestDto.getDescription();
        Long userId = todoRequestDto.getUserId();

        // 세터를 사용하여 할일 수정
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setUserId(userId);
        return todoRepository.save(todo);
    }

    // 할일 삭제
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}