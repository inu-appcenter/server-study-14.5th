package com.example.todolist.dto;

import com.example.todolist.domain.Member;
import com.example.todolist.domain.Todo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberResponseDto {
    private String email;
    private int age;
    private String name;
    private List<Todo> todoList;

    @Builder
    public MemberResponseDto(Member member) {
        this.email = member.getEmail();
        this.age = member.getAge();
        this.name = member.getName();
        this.todoList = member.getTodoList();
    }
}
