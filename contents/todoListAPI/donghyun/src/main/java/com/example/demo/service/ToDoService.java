package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.ToDo;
import com.example.demo.dto.ToDoSaveRequestDto;
import com.example.demo.dto.ToDoUpdateRequestDto;
import org.springframework.stereotype.Service;

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