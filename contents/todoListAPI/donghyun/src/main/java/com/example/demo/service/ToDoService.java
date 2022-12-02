package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.ToDo;
import com.example.demo.dto.todo.ToDoResponseDto;
import com.example.demo.dto.todo.ToDoSaveRequestDto;
import com.example.demo.dto.todo.ToDoUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface ToDoService {

    // todo 생성
    public Long saveToDo(String memberId, ToDoSaveRequestDto toDoSaveRequestDto);

    // todo 목록
    public List<ToDo> findToDos();

    // todo 조회
    public ToDo findById(Long toDoId);

    // todo 수정
    public void updateToDo(Long toDoId, ToDoUpdateRequestDto toDoUpdateRequestDto);

    // todo 삭제
    public void deleteToDo(Long toDoId);
}
