package com.appcenter.todolist.domain;

import com.appcenter.todolist.common.Timestamped;
import com.appcenter.todolist.dto.TodoRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Todo extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long todoId;

    @Column(nullable = false, length = 100)
    private String content;

    @Column(columnDefinition = "False", nullable = false)
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn
    private Member member;

    @Builder
    public Todo(String content, Boolean isCompleted, Member member) {
        this.content = content;
        this.isCompleted = isCompleted;
        this.member = member;
    }

    public Todo(TodoRequestDto todoRequestDto){
        this.content = todoRequestDto.getContents();
        this.isCompleted = todoRequestDto.getIsCompleted();
        this.member = todoRequestDto.getMember();
    }

    public void update(TodoRequestDto todoRequestDto){
        this.content = todoRequestDto.getContents();
        this.isCompleted = todoRequestDto.getIsCompleted();
        this.member = todoRequestDto.getMember();
    }

}
