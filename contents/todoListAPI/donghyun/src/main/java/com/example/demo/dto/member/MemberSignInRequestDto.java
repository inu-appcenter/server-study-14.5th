package com.example.demo.dto.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSignInRequestDto {

    private String memberId;

    private String password;

    public MemberSignInRequestDto(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}
