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

    @Column(nullable = false, length = 20)
    private String email;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Todo> todoList = new ArrayList<>();

    @Builder
    public Member(String email, int age, String name, List<Todo> todoList){
        this.email = email;
        this.age = age;
        this.name = name;
        this.todoList = todoList;
    }

    public Member(MemberRequestDto memberRequestDto){
        this.email = memberRequestDto.getEmail();
        this.age = memberRequestDto.getAge();
        this.name = memberRequestDto.getName();
        this.todoList = memberRequestDto.getTodoLists();
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
