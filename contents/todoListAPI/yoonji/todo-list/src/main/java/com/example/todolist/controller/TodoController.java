package com.example.todolist.controller;

import com.example.todolist.domain.Todo;
import com.example.todolist.dto.TodoResponseDto;
import com.example.todolist.dto.TodoSaveRequestDto;
import com.example.todolist.dto.TodoUpdateRequestDto;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //todo 생성
    @PostMapping("/new")
    public Long createTodo(@RequestBody TodoSaveRequestDto requestDto) {
        return todoService.create(requestDto);
    }

    //todo 전체 조회
    @GetMapping
    public List<Todo> findTodos() {
        return todoService.findTodo();
    }

    //todo 조회
    @GetMapping("/{todoId}")
    public TodoResponseDto findTodo(@PathVariable Long todoId) {
        return todoService.findOne(todoId);
    }

    //todo 수정
    @PutMapping("/{todoId}")
    public Long updateTodo(@PathVariable Long todoId, @RequestBody TodoUpdateRequestDto todoUpdateRequestDto) {
        return todoService.update(todoId, todoUpdateRequestDto);
    }

    //todo 삭제
    @DeleteMapping("/{todoId}")
    public Long deleteTodo(@PathVariable Long todoId) {
        return todoService.delete(todoId);
    }
}
