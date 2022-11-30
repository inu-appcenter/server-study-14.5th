package com.example.todo.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@ApiModel(value = "Login 요청")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class MemberLoginRequestDto {

    @Email
    @NotBlank(message = "이메일을 적어주세요")
    @ApiModelProperty(position = 1, required = true, dataType = "String", value = "이메일", example = "example@inu.ac.kr")
    private String email;

    @NotBlank(message = "비밀번호를 적어주세요")
    @ApiModelProperty(position = 2, required = true, dataType = "String", value = "비밀번호", example = "password!@#")
    private String password;
}
