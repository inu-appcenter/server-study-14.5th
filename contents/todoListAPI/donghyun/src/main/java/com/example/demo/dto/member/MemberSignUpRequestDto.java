package com.example.demo.dto.member;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSignUpRequestDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String memberId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바르지 않은 이메일 형식입니다.")
    private String email;
    @Min(value = 1, message = "나이는 1이상 입력해주세요.")
    private int age;
    @NotBlank(message = "권한을 입력해주세요.")
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
