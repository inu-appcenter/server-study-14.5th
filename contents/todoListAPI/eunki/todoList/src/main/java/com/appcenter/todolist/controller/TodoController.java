package com.appcenter.todolist.controller;

import com.appcenter.todolist.domain.Member;
import com.appcenter.todolist.domain.Todo;
import com.appcenter.todolist.dto.TodoRequestDto;
import com.appcenter.todolist.repository.TodoRepository;
import com.appcenter.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TodoController {
    private final TodoRepository todoRepository;
    private final TodoService todoService;

    //Todo 전체 조회
    @GetMapping("/todos")
    public List<Todo> readTodoList(){
        return todoRepository.findAllByOrderByModifiedAtDesc();
    }

    //Todo와 해당 Todo 작성한 Member 조회 - todoId 이용
    @GetMapping("/todos/{todoId}")
    public Member readMember(@PathVariable Long todoId){
        Todo todo = todoService.findTodo(todoId);
        return todo.getMember();
    }

    @PutMapping("/todos/{todoId}")
    public void updateTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto todoRequestDto){
        todoService.updateTodo(todoId, todoRequestDto);
    }

    //Todo 삭제
    @DeleteMapping("/todos/{todoId}")
    public void deleteTodo(@PathVariable Long todoId){
        todoRepository.deleteById(todoId);
    }
}
