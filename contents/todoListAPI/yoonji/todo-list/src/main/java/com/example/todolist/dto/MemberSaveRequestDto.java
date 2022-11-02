package com.example.todolist.dto;

import com.example.todolist.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {
    private Long id;
    private String email;
    private int age;
    private String name;


    public MemberSaveRequestDto(Long id, String email, int age, String name) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.name = name;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .email(email)
                .age(age)
                .name(name)
                .build();
    }
}
