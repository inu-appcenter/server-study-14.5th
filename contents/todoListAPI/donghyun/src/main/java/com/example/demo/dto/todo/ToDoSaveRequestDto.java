package com.example.demo.dto.todo;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDoSaveRequestDto {

    private String content;
    private Boolean isCompleted;

    public ToDoSaveRequestDto(String content, boolean isCompleted) {
        this.content = content;
        this.isCompleted = isCompleted;
    }
}
