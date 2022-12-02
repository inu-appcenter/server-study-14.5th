package com.example.demo.dto.todo;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDoSaveRequestDto {

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    @NotBlank(message = "완료 여부를 입력해주세요.")
    private Boolean isCompleted;

    public ToDoSaveRequestDto(String content, boolean isCompleted) {
        this.content = content;
        this.isCompleted = isCompleted;
    }
}
