package com.example.demo.dto.sign;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInResultDto extends SignUpResultDto {

    private String token;

    @Builder
    public SignInResultDto(Boolean success, int code, String msg, String token) {
        super(success, code, msg);
        this.token = token;
    }

}
