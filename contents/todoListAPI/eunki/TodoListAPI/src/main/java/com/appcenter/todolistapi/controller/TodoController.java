package com.appcenter.todolistapi.controller;

import com.appcenter.todolistapi.dto.TodoRequestDto;
import com.appcenter.todolistapi.dto.TodoResponseDtoForMember;
import com.appcenter.todolistapi.dto.TodoResponseDtoForTodo;
import com.appcenter.todolistapi.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

public class TodoController {
    private final TodoService todoService;

    @PostMapping("/todos")
    public void createTodo(@RequestBody TodoRequestDto todoRequestDto){
        todoService.createTodo(todoRequestDto);
    }

    @GetMapping("/todos/{todoId}")
    public TodoResponseDtoForTodo readTodo(@PathVariable Long todoId){
        return todoService.readTodo(todoId);
    }

    @PutMapping("/todos/{todoId}")
    public void updateTodo(@PathVariable Long todoId){
        todoService.updateTodo(todoId);
    }

    @DeleteMapping("todos/{todoId}")
    public void deleteTodo(@PathVariable Long todoId){
        todoService.deleteTodo(todoId);
    }
}
