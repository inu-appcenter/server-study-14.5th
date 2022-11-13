package com.example.todolist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoUpdateRequestDto {

    private Boolean isCompleted;

    public TodoUpdateRequestDto(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
