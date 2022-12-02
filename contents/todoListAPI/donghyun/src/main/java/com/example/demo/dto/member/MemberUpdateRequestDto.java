package com.example.demo.dto.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateRequestDto {

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @Min(value = 1, message = "나이는 1이상 입력해주세요.")
    private int age;

    public MemberUpdateRequestDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
