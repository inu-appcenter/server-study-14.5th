package com.example.demo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDoUpdateRequestDto {

    private boolean isCompleted;

    public ToDoUpdateRequestDto(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}