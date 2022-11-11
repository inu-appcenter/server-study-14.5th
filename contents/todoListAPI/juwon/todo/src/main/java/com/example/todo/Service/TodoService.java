package com.example.todo.Service;

import com.example.todo.Dto.TodoRequestDto;
import com.example.todo.Domain.Member;
import com.example.todo.Domain.Todo;
import com.example.todo.Dto.TodoResponseDto;
import com.example.todo.Repository.MemberRepository;
import com.example.todo.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    // todo 생성
    public Long create(TodoRequestDto todoDTO) {
        log.info("todo 생성 시작");
        Member member = memberRepository.findById(todoDTO.getMemberId()).get();
        Todo todo = todoDTO.toEntity(member);
        log.info("todo 생성 준비 | member & todo loaded");
        todoRepository.save(todo);
        log.info("todo 생성 완료 | id = " + todo.getId());
        return todo.getId();
    }

    // todo 조회 - 식별자로 조회
    public TodoResponseDto findById(Long id) {
        log.info("todo id로 조회 | id = " + id);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할 일 입니다"));
        log.info("todo id로 조회 완료 | id = " + id);
        return new TodoResponseDto(todo);
    }

    // todo 수정
    public void update(Long id) {
        log.info("todo 수정 | id = " + id);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 목록입니다"));
        log.info("todo 수정 준비 | id = " + id);
        todo.updateIsCompleted(todo.getIsCompleted());
        log.info("todo 수정 완료 | id = " + id);
    }

    // todo 삭제
    public void delete(Long id) {
        log.info("todo 삭제 | id = " + id);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 목록입니다"));
        log.info("todo 삭제 준비 | id = " + id);
        todoRepository.deleteById(id);
        log.info("todo 삭제 완료 | id = " + id);
    }
}
