package com.example.demo.dto;

import lombok.*;

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