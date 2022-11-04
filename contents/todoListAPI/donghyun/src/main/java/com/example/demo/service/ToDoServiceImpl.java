package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.ToDo;
import com.example.demo.dto.ToDoSaveRequestDto;
import com.example.demo.dto.ToDoUpdateRequestDto;
import com.example.demo.repository.JpaMemberRepository;
import com.example.demo.repository.JpaToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService {


    private final JpaMemberRepository memberRepository;
    private  final JpaToDoRepository toDoRepository;

    @Autowired
    public ToDoServiceImpl(JpaMemberRepository memberRepository, JpaToDoRepository toDoRepository) {
        this.memberRepository = memberRepository;
        this.toDoRepository = toDoRepository;
    }

    // todo 생성
    @Override
    public Long saveToDo (Long memberId, ToDoSaveRequestDto toDoSaveRequestDto) {
        Member member = memberRepository.findById(memberId).get();
        ToDo toDo = toDoSaveRequestDto.entity();
        toDo.setMember(member);

        toDoRepository.save(toDo);

        return toDo.getId();
    }

    // todo 조회
    @Override
    public Optional<ToDo> findById(Long toDoId) {
        return toDoRepository.findById(toDoId);
    }

    // todo 수정
    @Override
    public void updateToDo(Long toDoId, ToDoUpdateRequestDto toDoUpdateRequestDto) {
        ToDo toDo = toDoRepository.findById(toDoId).get();
        toDo.setCompleted(toDoUpdateRequestDto.isCompleted());

        toDoRepository.save(toDo);
    }

    // todo 삭제
    @Override
    public void deleteToDo(Long toDoId) {
        toDoRepository.deleteById(toDoId);
    }
}