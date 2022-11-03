package com.appcenter.todolist.dto;

import com.appcenter.todolist.domain.Todo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class MemberRequestDto {
    private Long memberId;
    private String email;
    private int age;
    private String name;
    private List<Todo> todoLists;
}
