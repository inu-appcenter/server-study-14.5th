package com.example.todolist.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Todo extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private boolean isCompleted;

    @ManyToOne
    @JoinColumn
    private Member member;

    @Builder
    public Todo(Long id, String content, boolean isCompleted, Member member) {
        this.id = id;
        this.content = content;
        this.isCompleted = isCompleted;
        this.member = member;
    }

    public void update(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}