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

    @PostMapping
    @ResponseBody
    public Todo createTodo(@RequestBody TodoRequestDto todoRequestDto) {
        return todoService.createTodo(todoRequestDto);
    }

    @GetMapping
    public String todo(Model model) {
        List<Todo> todos = todoService.getTodos(); // Assume getTodos() returns a list of all todos
        model.addAttribute("todos", todos);
        return "todo";
    }
}
