package com.example.demo.dto.todo;

import com.example.demo.domain.ToDo;
import lombok.Data;

@Data
public class ToDoDto {

    private Long id;

    private String content;

    private Boolean isCompleted;

    public ToDoDto(ToDo todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.isCompleted = todo.getIsCompleted();
    }
}
