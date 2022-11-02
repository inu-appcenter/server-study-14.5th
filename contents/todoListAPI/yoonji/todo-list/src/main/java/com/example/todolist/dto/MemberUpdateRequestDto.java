package com.example.todolist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private int age;
    private String name;

    public MemberUpdateRequestDto(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
