package com.example.todolist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {
    private String email;
    private String password;
    private int age;
    private String name;

    public MemberSaveRequestDto(String email, String password, int age, String name) {
        this.email = email;
        this.password = password;
        this.age = age;
        this.name = name;
    }

    /*public Member toEntity() {
        return Member.builder()
                .password(password)
                .email(email)
                .age(age)
                .name(name)
                .build();
    }*/
}
