package com.example.todo.Dto;

import com.example.todo.Domain.Member;
import com.example.todo.Domain.Todo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@ApiModel("Todo 생성 요청")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TodoSaveRequestDto {
    @Length(max = 100, message = "내용의 길이는 최대 100글자 입니다")
    @NotBlank(message = "내용을 입력해주세요")
    @ApiModelProperty(position = 1, required = true, dataType = "String", value = "할 일 내용", example = "숨 쉬기")
    private String contents;

    @NotBlank(message = "true 또는 false로 값을 입력해주세요")
    @ApiModelProperty(position = 2, required = true, dataType = "Boolean", value = "완료여부", example = "false")
    private Boolean isCompleted;

    public Todo toEntity(Member member) {
        return Todo.builder()
                .contents(contents)
                .isCompleted(isCompleted)
                .member(member)
                .build();
    }

}
