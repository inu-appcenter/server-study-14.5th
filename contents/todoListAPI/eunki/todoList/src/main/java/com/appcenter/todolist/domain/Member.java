package com.appcenter.todolist.domain;

import com.appcenter.todolist.common.Timestamped;
import com.appcenter.todolist.dto.MemberRequestDto;
import com.appcenter.todolist.dto.TodoRequestDto;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
//기본생성자 접근제어를 PROTECTED로 -> @NonNull인 필드 누락 방지
@Entity
@Getter
public class Member extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long memberId;


    @Length(max = 20) //validation
    @Column(columnDefinition = "varchar(20) not null") //DDL
    private String email;


    @Positive
    @Column(columnDefinition = "default 1 not null")
    private int age;

    @Length(max = 20)
    @Column(columnDefinition = "varchar(20) default 'NoName' not null")
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
