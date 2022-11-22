package com.example.todolist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoUpdateRequestDto {

    private Boolean isCompleted;
    private String content;

    public TodoUpdateRequestDto(Boolean isCompleted, String content) {
        this.isCompleted = isCompleted;
        this.content = content;
    }
}
