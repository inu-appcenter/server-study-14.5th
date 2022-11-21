package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.ToDo;
import com.example.demo.dto.todo.ToDoSaveRequestDto;
import com.example.demo.dto.todo.ToDoUpdateRequestDto;
import com.example.demo.repository.JpaMemberRepository;
import com.example.demo.repository.JpaToDoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
    public Long saveToDo (String memberId, ToDoSaveRequestDto toDoSaveRequestDto) {
        Member member = memberRepository.getByMemberId(memberId).get();
        ToDo toDo = ToDo.createTodo(toDoSaveRequestDto.getContent(), toDoSaveRequestDto.getIsCompleted(), member);

        toDoRepository.save(toDo);
        return toDo.getId();
    }

    @Override
    public List<ToDo> findToDos() {
        return toDoRepository.findAll();
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
        toDo.setCompleted();

        toDoRepository.save(toDo);
    }

    // todo 삭제
    @Override
    public void deleteToDo(Long toDoId) {
        toDoRepository.deleteById(toDoId);
    }
}
