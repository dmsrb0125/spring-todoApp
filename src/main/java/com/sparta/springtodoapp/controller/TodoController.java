package com.sparta.springtodoapp.controller;

import com.sparta.springtodoapp.dto.TodoRequestDto;
import com.sparta.springtodoapp.entity.Todo;
import com.sparta.springtodoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // 할일 페이지 연결, 전체 할일 조회
    @GetMapping
    public String todo(Model model) {
        List<Todo> todos = todoService.getTodos(); // 저장된 할일 목록들
        model.addAttribute("todos", todos);
        return "todo";
    }

    // 할일 등록 요청
    @PostMapping
    @ResponseBody
    public Todo createTodo(@RequestBody TodoRequestDto todoRequestDto) {
        return todoService.createTodo(todoRequestDto);
    }

    // 특정 할일 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = todoService.getTodoById(id);
        return todo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // 할일 수정 요청
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto todoRequestDto) {
        Optional<Todo> updatedTodo = todoService.updateTodo(id, todoRequestDto);
        return updatedTodo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 할일 삭제 요청
    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}


