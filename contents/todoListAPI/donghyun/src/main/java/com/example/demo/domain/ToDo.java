package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public ToDo(String content, boolean isCompleted, Member member) {
        this.content = content;
        this.isCompleted = isCompleted;
        this.member = member;
    }

    public static ToDo createTodo(String content, Boolean isCompleted, Member member) {
        ToDo toDo = new ToDo();
        toDo.content = content;
        toDo.isCompleted = isCompleted;
        toDo.member = member;
        return toDo;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCompleted() {
        this.isCompleted = !isCompleted;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}

