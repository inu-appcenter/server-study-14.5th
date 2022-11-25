package com.appcenter.todolistapi.service;

import com.appcenter.todolistapi.domain.Member;
import com.appcenter.todolistapi.domain.Todo;
import com.appcenter.todolistapi.dto.TodoRequestDto;
import com.appcenter.todolistapi.dto.TodoResponseDtoForMember;
import com.appcenter.todolistapi.dto.TodoResponseDtoForTodo;
import com.appcenter.todolistapi.repository.MemberRepository;
import com.appcenter.todolistapi.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    public void createTodo(TodoRequestDto todoRequestDto){
        Member member = memberRepository.findById(todoRequestDto.getMemberId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다")
        );
        Todo todo = todoRepository.save(todoRequestDto.toEntity(member));
    }

    public TodoResponseDtoForTodo readTodo(Long todoId){
        Todo todo =  todoRepository.findById(todoId).orElseThrow(
                () -> new RuntimeException("목록에 없는 할 일 입니다")
        );
        Member member = todo.getMember();
        return new TodoResponseDtoForTodo(todo, member);
    }

    public void updateTodo(Long todoId){
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new RuntimeException("목록에 없는 할 일 입니다")
        ) ;
        todo.update();
        todoRepository.save(todo);
    }

    public void deleteTodo(Long todoId){
        todoRepository.findById(todoId).orElseThrow(
                () -> new RuntimeException("목록에 없는 할 일 입니다")
        );
        todoRepository.deleteById(todoId);
    }
}
