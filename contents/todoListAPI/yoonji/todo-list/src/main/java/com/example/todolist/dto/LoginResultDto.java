package com.example.todolist.dto;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class LoginResultDto {
    private Long memberId;
    private String token;

    @Builder
    public LoginResultDto(Long memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }
}
