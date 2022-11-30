package com.example.todo.Dto;

import io.swagger.annotations.ApiModel;
import lombok.*;


@Getter
@ApiModel(value = "Login 응답")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLoginResultDto {
    private Long id;
    private String token;
}
