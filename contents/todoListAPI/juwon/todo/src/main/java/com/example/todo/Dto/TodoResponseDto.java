package com.example.todo.Dto;

import com.example.todo.Domain.Todo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@ApiModel(value = "Todo 응답")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoResponseDto {
    @ApiModelProperty(position = 1, required = true, dataType = "Long", value = "식별자", example = "1")
    private Long id;

    @ApiModelProperty(position = 2, required = true, dataType = "Long", value = "멤버 식별자", example = "1")
    private Long memberId;

    @ApiModelProperty(position = 3, required = true, dataType = "String", value = "할 일 내용", example = "숨 쉬기")
    private String content;

    @ApiModelProperty(position = 4, required = true, dataType = "Boolean", value = "완료여부", example = "false")
    private boolean isCompleted;

    public TodoResponseDto(Todo todo){
        this.id = todo.getId();
        this.memberId = todo.getMember().getId();
        this.content = todo.getContents();
        this.isCompleted = todo.getIsCompleted();
    }
}