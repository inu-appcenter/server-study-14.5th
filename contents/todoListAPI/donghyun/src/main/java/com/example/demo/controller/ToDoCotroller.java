package com.example.demo.controller;

import com.example.demo.domain.ToDo;
import com.example.demo.dto.todo.ToDoResponseDto;
import com.example.demo.dto.todo.ToDoSaveRequestDto;
import com.example.demo.dto.todo.ToDoUpdateRequestDto;
import com.example.demo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ToDoController {

    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    // todo 생성
    @PostMapping("/members/{memberId}/todos")
    public Long saveToDo (@PathVariable Long memberId, @RequestBody ToDoSaveRequestDto toDoSaveRequestDto) {
        Long saveToDo = toDoService.saveToDo(memberId, toDoSaveRequestDto);

        return saveToDo;
    }

    // todo 조회
    @GetMapping("/todos/{toDoId}")
    public ToDoResponseDto findById (@PathVariable Long toDoId) {
        ToDo toDo = toDoService.findById(toDoId).get();

        return new ToDoResponseDto(toDo);
    }

    // todo 수정
    @PatchMapping("/todos/{toDoId}")
    public void updateToDo (@PathVariable Long toDoId, @RequestBody ToDoUpdateRequestDto toDoUpdateRequestDto) {
        toDoService.updateToDo(toDoId, toDoUpdateRequestDto);
    }

    // todo 삭제
    @DeleteMapping("/todos/{toDoId}")
    public void deleteToDo (@PathVariable Long toDoId) {
        toDoService.deleteToDo(toDoId);
    }
}