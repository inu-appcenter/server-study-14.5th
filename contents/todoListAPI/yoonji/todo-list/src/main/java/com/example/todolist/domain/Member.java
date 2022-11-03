package com.example.todolist.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private int age;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<Todo> todoList = new ArrayList<>();

    @Builder
    public Member(Long id, String email, int age, String name, List<Todo> todoList) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.name = name;
        this.todoList = todoList;
    }

    public void update(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
