package com.example.todolist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Member member;

    @Builder
    public Todo(Long id, String content, Boolean isCompleted, Member member) {
        this.id = id;
        this.content = content;
        this.isCompleted = isCompleted;
        this.member = member;
    }

    public void update(Boolean isCompleted, String content) {
        this.isCompleted = isCompleted;
        this.content = content;
    }
}