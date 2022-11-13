package com.example.todolist.service;

import com.example.todolist.domain.Member;
import com.example.todolist.domain.Todo;
import com.example.todolist.dto.*;
import com.example.todolist.repository.SpringDataJpaMemberRepository;
import com.example.todolist.repository.SpringDataJpaTodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TodoService {
    private final SpringDataJpaTodoRepository todoRepository;
    private final SpringDataJpaMemberRepository memberRepository;

    @Autowired
    public TodoService(SpringDataJpaTodoRepository todoRepository, SpringDataJpaMemberRepository memberRepository) {
        this.todoRepository = todoRepository;
        this.memberRepository = memberRepository;
    }

    //todo 생성
    public Long create(TodoSaveRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).get();

        return todoRepository.save(requestDto.toEntity(member)).getId();
    }

    //todo 전체 조회
    public List<Todo> findTodo() {
        return todoRepository.findAll();
    }

    //todo 조회
    public TodoResponseDto findOne(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("할 일이 존재하지 않습니다."));

        return new TodoResponseDto(todo);
    }

    // todo 수정
    public Long update(Long todoId, TodoUpdateRequestDto requestDto) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 목록입니다."));

        todo.update(requestDto.getIsCompleted());

        return todoRepository.save(todo).getId();
    }

    //todo 삭제
    public Long delete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 목록입니다."));

        todoRepository.deleteById(id);

        return todo.getId();
    }
}
