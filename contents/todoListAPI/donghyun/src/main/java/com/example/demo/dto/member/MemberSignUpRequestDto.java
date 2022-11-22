package com.example.demo.dto.member;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSignUpRequestDto {

    private String memberId;

    private String password;
    private String name;
    private String email;
    private int age;

    private String role;


    public MemberSignUpRequestDto(String memberId, String password, String name, String email, int age, String role) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.age = age;
        this.role = role;
    }
}
