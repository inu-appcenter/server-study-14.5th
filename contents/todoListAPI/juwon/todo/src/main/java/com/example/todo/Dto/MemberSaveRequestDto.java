package com.example.todo.Dto;

import com.example.todo.Domain.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@ApiModel(value = "Member 생성 요청")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class MemberSaveRequestDto {

    @Length(max = 20, message = "이름은 20자 이내로 입력해주세요")
    @NotBlank(message = "이름을 적어주세요")
    @ApiModelProperty(position = 1, required = true, dataType = "String", value = "이름", example = "이주원")
    private String name;

    @Positive(message = "잘못된 나이를 입력했습니다")
    @NotNull(message = "나이를 적어주세요")
    @ApiModelProperty(position = 2, required = true, dataType = "int", value = "나이", example = "20")
    private int age;

    @Email
    @NotBlank(message = "이메일을 적어주세요")
    @ApiModelProperty(position = 3, required = true, dataType = "String", value = "이메일", example = "example@inu.ac.kr")
    private String email;

    @NotBlank(message = "비밀번호를 적어주세요")
    @ApiModelProperty(position = 4, required = true, dataType = "String", value = "비밀번호", example = "password!@#")
    private String password;

    @NotBlank(message = "역할은 USER와 ADMIN이 있습니다")
    @ApiModelProperty(position = 5, required = true, dataType = "String", value = "역할", example = "USER")
    private String role;

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .name(name)
                .age(age)
                .email(email)
                .password(encodedPassword)
                .role(role)
                .build();
    }

}