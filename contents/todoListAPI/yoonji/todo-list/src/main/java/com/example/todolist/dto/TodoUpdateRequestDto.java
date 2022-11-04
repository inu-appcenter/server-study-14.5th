package com.example.todolist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoUpdateRequestDto {

    private boolean isCompleted;

    public TodoUpdateRequestDto(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
