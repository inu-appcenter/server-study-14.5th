package com.appcenter.todolist.dto;

import com.appcenter.todolist.domain.Member;
import lombok.Getter;

@Getter
public class TodoRequestDto {
    private Long todoId;
    private String contents;
    private Boolean isCompleted;
    private Member member;
}
