package com.appcenter.todolistapi.dto;

import com.appcenter.todolistapi.domain.Member;
import com.appcenter.todolistapi.domain.Todo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@RequiredArgsConstructor
public class TodoResponseDtoForTodo {
    private Long todoId;
    @Length(max = 10)
    private String title;
    @Length(max = 100)
    private String content;
    private boolean isCompleted;
    private MemberResponseDtoExceptForTodoList memberResponseDtoExceptForTodoList;

    public TodoResponseDtoForTodo(Todo todo, Member member){
        this.title = todo.getTitle();
        this.todoId = todo.getTodoId();
        this.content = todo.getContent();
        this.isCompleted = todo.isCompleted();
        this.memberResponseDtoExceptForTodoList = new MemberResponseDtoExceptForTodoList(member);
    }
}
