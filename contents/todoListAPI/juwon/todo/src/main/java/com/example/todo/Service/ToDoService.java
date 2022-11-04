package com.example.todo.Service;

import com.example.todo.DTO.ToDoDTO;
import com.example.todo.Domain.Member;
import com.example.todo.Domain.ToDo;
import com.example.todo.Repository.MemberRepository;
import com.example.todo.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ToDoService(TodoRepository todoRepository, MemberRepository memberRepository) {
        this.todoRepository = todoRepository;
        this.memberRepository = memberRepository;
    }

    // todo 생성
    public Long create(ToDoDTO todoDTO) {
        Member member = memberRepository.findById(todoDTO.getMemberId()).get();

        return todoRepository.save(todoDTO.toEntity(member)).getId();
    }

    // todo 조회 - 식별자로 조회
    public ToDoDTO findById(Long todoId) {
        ToDo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할 일 입니다"));
        return new ToDoDTO(todo);
    }

    // todo 수정
    public Long update(Long id, ToDoDTO todoDTO) {
        ToDo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 목록입니다"));
        todo.update(todoDTO.getIsCompleted());
        return todoRepository.save(todo).getId();
    }

    // todo 삭제
    public Long delete(Long id) {
        ToDo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 목록입니다"));

        todoRepository.deleteById(id);
        return todo.getId();
    }
}
