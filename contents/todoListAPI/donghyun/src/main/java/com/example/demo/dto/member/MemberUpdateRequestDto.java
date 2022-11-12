package com.example.demo.dto.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateRequestDto {

    private String name;
    private int age;

    public MemberUpdateRequestDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}