package com.example.demo.service;

import com.example.demo.domain.ToDo;
import com.example.demo.dto.todo.ToDoSaveRequestDto;
import com.example.demo.dto.todo.ToDoUpdateRequestDto;

import java.util.Optional;

public interface ToDoService {

    // todo 생성
    public Long saveToDo(Long memberId, ToDoSaveRequestDto toDoSaveRequestDto);

    // todo 조회
    public Optional<ToDo> findById(Long toDoId);

    // todo 수정
    public void updateToDo(Long toDoId, ToDoUpdateRequestDto toDoUpdateRequestDto);

    // todo 삭제
    public void deleteToDo(Long toDoId);
}