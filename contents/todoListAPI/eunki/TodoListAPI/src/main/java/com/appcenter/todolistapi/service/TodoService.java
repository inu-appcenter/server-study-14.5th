package com.appcenter.todolistapi.service;

import com.appcenter.todolistapi.domain.Member;
import com.appcenter.todolistapi.domain.Todo;
import com.appcenter.todolistapi.dto.TodoRequestDtoCreate;
import com.appcenter.todolistapi.dto.TodoResponseDto;
import com.appcenter.todolistapi.repository.MemberRepository;
import com.appcenter.todolistapi.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    public void createTodo(TodoRequestDtoCreate todoRequestDtoCreate){
        Member member = memberRepository.findById(todoRequestDtoCreate.getMemberId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다")
        );
        Todo todo = todoRepository.save(todoRequestDtoCreate.toEntity(member));
    }

    public TodoResponseDto readTodo(Long todoId){
        Todo todo =  todoRepository.findById(todoId).orElseThrow(
                () -> new RuntimeException("목록에 없는 할 일 입니다")
        );
        return new TodoResponseDto(todo);
    }

    public Todo updateTodo(Long todoId){
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다")
        );
        todo.updateIsCompleted();
        return todo;
    }

    public void deleteTodo(Long todoId){
        todoRepository.findById(todoId).orElseThrow(
                () -> new RuntimeException("목록에 없는 할 일 입니다")
        );
        todoRepository.deleteById(todoId);
    }
}
