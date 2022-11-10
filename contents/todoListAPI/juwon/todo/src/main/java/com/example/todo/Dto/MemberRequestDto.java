package com.example.todo.Dto;

import com.example.todo.Domain.Member;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;

@Getter
public class MemberRequestDto {
    @Length(max = 20, message = "이름은 20자 이내로 입력해주세요")
    private String name;

    @Positive(message = "나이를 다시 입력해주세요")
    private int age;

    @Email(message = "이메일 형식에 맞게 입력해주세요")
    private String email;

    @Builder
    public MemberRequestDto(Member member) {
        this.name = member.getName();
        this.age = member.getAge();
        this.email = member.getEmail();
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .age(age)
                .email(email)
                .build();
    }

}