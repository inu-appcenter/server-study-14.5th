package com.example.demo.controller;

import com.example.demo.domain.ToDo;
import com.example.demo.dto.ToDoSaveRequestDto;
import com.example.demo.dto.ToDoUpdateRequestDto;
import com.example.demo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping("/todos")
public class ToDoController {

    private ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    // todo 생성
    @PostMapping("/members/{member-id}/todos")
    public Long saveToDo (@PathVariable Long memberId, @RequestBody ToDoSaveRequestDto toDoSaveRequestDto) {
        Long saveToDo = toDoService.saveToDo(memberId, toDoSaveRequestDto);
        return saveToDo;
    }

    // todo 조회
    @GetMapping("/{todo-id}")
    public Optional<ToDo> findById (@PathVariable Long toDoId) {
        Optional<ToDo> toDo = toDoService.findById(toDoId);
        return toDo;
    }

    // todo 수정
    @PatchMapping("/{todo-id}")
    public void updateToDo (@PathVariable Long toDoId, @RequestBody ToDoUpdateRequestDto toDoUpdateRequestDto) {
        toDoService.updateToDo(toDoId, toDoUpdateRequestDto);
    }

    // todo 삭제
    @DeleteMapping("/{todo-id}")
    public void deleteToDo (@PathVariable Long toDoId) {
        toDoService.deleteToDo(toDoId);
    }
}