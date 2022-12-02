package com.example.demo.dto.member;

import com.example.demo.domain.Member;
import com.example.demo.dto.todo.ToDoDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {
    private Long id;

    private String name;

    private String email;

    private int age;

    private List<ToDoDto> toDoList;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.age = member.getAge();
        this.name = member.getName();
        this.toDoList = member.getToDoList().stream()
                .map(todo -> new ToDoDto(todo))
                .collect(Collectors.toList());
    }
}