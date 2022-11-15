package com.example.demo.dto.member;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSaveRequestDto {
    private String name;
    private String email;
    private int age;


    public MemberSaveRequestDto(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}