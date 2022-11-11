package com.example.todo.Dto;

import com.example.todo.Domain.Todo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@RequiredArgsConstructor
public class TodoResponseDto {
    private Long id;
    @Length(max = 100, message = "내용의 길이는 최대 100글자 입니다")
    private String content;
    private boolean isCompleted;

    public TodoResponseDto(Todo todo){
        this.id = todo.getId();
        this.content = todo.getContents();
        this.isCompleted = todo.getIsCompleted();
    }
}