package com.sparta.springtodoapp.service;

import com.sparta.springtodoapp.dto.TodoRequestDto;
import com.sparta.springtodoapp.entity.Todo;
import com.sparta.springtodoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // 할일 생성
    public Todo createTodo(TodoRequestDto todoRequestDto) {
        Todo todo = new Todo(
                todoRequestDto.getTitle(),
                todoRequestDto.getDescription(),
                todoRequestDto.getUserId()
        ); // 생성자 사용
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
            // 세터사용
            todo.setTitle(todoRequestDto.getTitle());
            todo.setDescription(todoRequestDto.getDescription());
            todo.setUserId(todoRequestDto.getUserId());
            return todoRepository.save(todo);
        }); // Optional를 사용하여 구현
    }

    // 할일 삭제
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }


}