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
    private Long id;
    private String content;
    private boolean isCompleted;

    public TodoSaveRequestDto(Long id, String content, boolean isCompleted) {
        this.id = id;
        this.content = content;
        this.isCompleted = isCompleted;
    }

    public Todo toEntity(Member member) {
        return Todo.builder()
                .id(id)
                .content(content)
                .isCompleted(isCompleted)
                .member(member)
                .build();
    }
}
