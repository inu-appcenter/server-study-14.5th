package com.example.todolist.controller;

import com.example.todolist.domain.Todo;
import com.example.todolist.dto.MemberUpdateRequestDto;
import com.example.todolist.dto.TodoResponseDto;
import com.example.todolist.dto.TodoSaveRequestDto;
import com.example.todolist.dto.TodoUpdateRequestDto;
import com.example.todolist.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"ToDo"})
@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @ApiOperation("ToDo 생성")
    @PostMapping("/new")
    public Long createTodo(@RequestBody TodoSaveRequestDto requestDto) {
        return todoService.create(requestDto);
    }

    @ApiOperation("ToDo 전체 조회")
    @GetMapping
    public List<Todo> findTodos() {
        return todoService.findTodo();
    }

    @ApiOperation("ToDo 조회")
    @GetMapping("/{todoId}")
    public TodoResponseDto findTodo(@PathVariable Long todoId) {
        return todoService.findOne(todoId);
    }

    @ApiOperation("ToDo 완료여부 변경")
    @PutMapping("/{todoId}")
    public Long updateTodo(@PathVariable Long todoId, @RequestBody TodoUpdateRequestDto requestDto) {
        return todoService.update(todoId, requestDto);
    }

    @ApiOperation("ToDo 삭제")
    @DeleteMapping("/{todoId}")
    public Long deleteTodo(@PathVariable Long todoId) {
        return todoService.delete(todoId);
    }
}
