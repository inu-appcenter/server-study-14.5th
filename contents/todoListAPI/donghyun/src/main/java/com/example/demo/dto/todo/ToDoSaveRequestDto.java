package com.example.demo.dto.todo;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDoSaveRequestDto {

    private Long id;
    private String content;
    private Boolean isCompleted;

    public ToDoSaveRequestDto(Long id, String content, boolean isCompleted) {
        this.id = id;
        this.content = content;
        this.isCompleted = isCompleted;
    }
}