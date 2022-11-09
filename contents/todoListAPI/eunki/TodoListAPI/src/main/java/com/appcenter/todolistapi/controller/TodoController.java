package com.appcenter.todolistapi.controller;

import com.appcenter.todolistapi.domain.Todo;
import com.appcenter.todolistapi.dto.TodoRequestDtoCreate;
import com.appcenter.todolistapi.dto.TodoResponseDto;
import com.appcenter.todolistapi.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

public class TodoController {
    private final TodoService todoService;

    @PostMapping("/todos")
    public void createTodo(@RequestBody TodoRequestDtoCreate todoRequestDtoCreate){
        todoService.createTodo(todoRequestDtoCreate);
    }

    @GetMapping("/todos/{todoId}")
    public TodoResponseDto readTodo(@PathVariable Long todoId){
        return todoService.readTodo(todoId);
    }

    @PutMapping("/todos/{todoId}")
    public Todo updateTodo(@PathVariable Long todoId){
        return todoService.updateTodo(todoId);
    }

    @DeleteMapping("todos/{todoId}")
    public void deleteTodo(@PathVariable Long todoId){
        todoService.deleteTodo(todoId);
    }
}
