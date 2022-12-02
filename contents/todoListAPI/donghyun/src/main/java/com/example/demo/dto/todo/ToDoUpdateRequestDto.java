package com.example.demo.dto.todo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDoUpdateRequestDto {

    @NotBlank(message = "완료 여부를 입력해주세요.")
    private boolean isCompleted;

    public ToDoUpdateRequestDto(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
