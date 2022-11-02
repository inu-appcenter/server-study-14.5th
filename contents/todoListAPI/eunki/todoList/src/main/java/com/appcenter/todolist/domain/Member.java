package com.appcenter.todolist.domain;

import com.appcenter.todolist.common.Timestamped;
import com.appcenter.todolist.dto.MemberRequestDto;
import com.appcenter.todolist.dto.TodoRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class Member extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long memberId;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String email;

    @Column(nullable = false)
    private int age;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String name;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Todo> todoList = new ArrayList<>();

    public Member(MemberRequestDto memberRequestDto){
        this.email = memberRequestDto.getEmail();
        this.age = memberRequestDto.getAge();
        this.name = memberRequestDto.getName();
        this.todoList = memberRequestDto.getTodoLists();
    }

    @Builder
    public Member(String email, int age, String name){
        this.email = email;
        this.age = age;
        this.name = name;
    }

    public static Member createMember(String email, int age, String name){
        return Member.builder()
                .email(email)
                .age(age)
                .name(name)
                .build();
    }

    public void update(MemberRequestDto memberRequestDto){
        this.email = memberRequestDto.getEmail();
        this.age = memberRequestDto.getAge();
        this.name = memberRequestDto.getName();
        this.todoList = memberRequestDto.getTodoLists();
    }

    public void planTodo(Todo todo){
        this.todoList.add(todo);
    }
}
