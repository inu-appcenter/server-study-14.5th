package com.example.demo.dto.sign;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInResultDto extends SignUpResultDto {

    private String token;

    @Builder
    public SignInResultDto(Boolean success,String msg, String token) {
        super(success, msg);
        this.token = token;
    }

}
