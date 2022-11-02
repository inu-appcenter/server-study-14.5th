package com.example.todolist.dto;

import com.example.todolist.domain.Member;
import com.example.todolist.domain.Todo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoResponseDto {
    private String content;
    private boolean isCompleted;
    private Member member;

    @Builder
    public TodoResponseDto(Todo todo) {
        this.content = todo.getContent();
        this.isCompleted = todo.isCompleted();
        this.member = todo.getMember();
    }
}
