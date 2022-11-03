package com.example.todo.DTO;

import com.example.todo.Domain.Member;
import lombok.Builder;
import lombok.Getter;
import com.example.todo.Domain.ToDo;

import java.util.List;

@Getter
public class MemberDTO {
    private Long id;
    private String name;
    private int age;
    private String email;
    private List<ToDo> toDoList;

    @Builder
    public MemberDTO(Member member) {
        this.name = member.getName();
        this.age = member.getAge();
        this.email = member.getEmail();
        this.toDoList = member.getToDoList();
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .name(name)
                .age(age)
                .email(email)
                .toDoList(toDoList)
                .build();
    }

}