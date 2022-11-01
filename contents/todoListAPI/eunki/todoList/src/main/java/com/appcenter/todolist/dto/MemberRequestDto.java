package com.appcenter.todolist.dto;

import com.appcenter.todolist.domain.Todo;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberRequestDto {
    private Long memberId;
    private String email;
    private int age;
    private String name;
    private List<Todo> todoLists;
}
