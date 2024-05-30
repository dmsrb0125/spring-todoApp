package com.sparta.springtodoapp.controller;

import com.sparta.springtodoapp.dto.TodoRequestDto;
import com.sparta.springtodoapp.entity.Todo;
import com.sparta.springtodoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping
    public String todo(Model model) {
        List<Todo> todos = todoService.getTodos(); // 저장된 할일 목록들
        model.addAttribute("todos", todos);
        return "todo";
    }

    @PostMapping
    @ResponseBody
    public Todo createTodo(@RequestBody TodoRequestDto todoRequestDto) {
        return todoService.createTodo(todoRequestDto);
    }


    @PutMapping("/{id}")
    @ResponseBody
    public Todo updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto todoRequestDto) {
        return todoService.updateTodo(id, todoRequestDto);
    }

}
