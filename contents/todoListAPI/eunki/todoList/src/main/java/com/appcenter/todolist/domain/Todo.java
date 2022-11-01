package com.appcenter.todolist.domain;

import com.appcenter.todolist.common.Timestamped;
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

    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String content;

    @Column(columnDefinition = "False", nullable = false)
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn
    private Member member;

    @Builder
    public Todo(String content, Boolean isCompleted, Member member){
        this.content = content;
        this. isCompleted = isCompleted;
        this. member = member;
    }

    public static Todo createTodo(String content, Boolean isCompleted){
        return Todo.builder()
                .content(content)
                .isCompleted(isCompleted)
                .build();
    }

}
