package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String email;
    @Column(nullable = false) // @Column을 사용한다면 기본값이 nullable = true 이다. 따라서 자바 기본 타입에 @Column을 사용한다면 nullable = false로 저장하는 것이 안전함
    private int age;
    @OneToMany(mappedBy = "member")
    private List<ToDo> toDoList = new ArrayList<>();

    @Builder
    public Member(Long id, String name, String email, int age, List<ToDo> toDoList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.toDoList = toDoList;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setAge(int age) {
        this.age = age;
    }


    public void setToDoList(List<ToDo> toDoList) {
        this.toDoList = toDoList;
    }
}
