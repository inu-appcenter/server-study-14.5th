package com.example.todolist.dto;

import com.example.todolist.domain.Member;
import com.example.todolist.domain.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoSaveRequestDto {
    private Long memberId;
    private String content;
    private Boolean isCompleted;

    public TodoSaveRequestDto(String content, Boolean isCompleted) {
        this.content = content;
        this.isCompleted = isCompleted;
    }

    public Todo toEntity(Member member) {
        return Todo.builder()
                .content(content)
                .isCompleted(isCompleted)
                .member(member)
                .build();
    }
}
