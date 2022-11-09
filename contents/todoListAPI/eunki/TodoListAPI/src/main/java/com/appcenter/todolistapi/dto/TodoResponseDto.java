package com.appcenter.todolistapi.dto;

import com.appcenter.todolistapi.domain.Member;
import com.appcenter.todolistapi.domain.Todo;
import com.appcenter.todolistapi.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;

@Getter
@RequiredArgsConstructor
public class TodoResponseDto {
    private Long todoId;
    @Length(max = 10)
    private String title;
    @Length(max = 100)
    private String content;
    private boolean isCompleted;

    public TodoResponseDto(Todo todo){
        this.title = todo.getTitle();
        this.todoId = todo.getTodoId();
        this.content = todo.getContent();
        this.isCompleted = todo.isCompleted();
    }
}
