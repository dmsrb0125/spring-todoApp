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

    public Todo createTodo(TodoRequestDto todoRequestDto) {
        String title = todoRequestDto.getTitle();
        String description = todoRequestDto.getDescription();

        // 생성자를 사용하여Todo 객체 생성
        Todo todo = new Todo(title, description);
        return todoRepository.save(todo);
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }
}