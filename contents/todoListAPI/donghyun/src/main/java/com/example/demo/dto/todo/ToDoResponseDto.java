package com.example.demo.dto.todo;

import com.example.demo.domain.ToDo;
import com.example.demo.dto.member.MemberResponseDto;
import lombok.Data;

@Data
public class ToDoResponseDto {

    private Long id;

    private String content;

    private Boolean isCompleted;

    private MemberResponseDto member;

    public ToDoResponseDto (ToDo todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.isCompleted = todo.getIsCompleted();
        this.member = new MemberResponseDto(todo.getMember());
    }
}