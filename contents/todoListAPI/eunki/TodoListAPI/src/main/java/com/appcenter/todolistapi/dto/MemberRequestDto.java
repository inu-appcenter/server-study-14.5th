package com.appcenter.todolistapi.dto;

import com.appcenter.todolistapi.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;

@Getter
@RequiredArgsConstructor
public class MemberRequestDto {
    @Length(max = 20, message = "이름을 20자 이하로 적어주세요")
    private String name;
    @Positive(message = "나이를 다시 적어주세요")
    private int age;
    @Email(message = "이메일 형식이 잘못되었습니다")
    private String email;

    @Builder
    public MemberRequestDto(String name, int age, String email){
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Member toEntity(){
        return Member.builder()
                .name(name)
                .age(age)
                .email(email)
                .build();
    }
}
