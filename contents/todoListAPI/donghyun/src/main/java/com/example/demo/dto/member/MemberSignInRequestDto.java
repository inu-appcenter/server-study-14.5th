package com.example.demo.dto.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSignInRequestDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String memberId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    public MemberSignInRequestDto(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}
