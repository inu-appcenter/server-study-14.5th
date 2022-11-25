package com.appcenter.todolistapi.domain;

import com.appcenter.todolistapi.common.Timestamped;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Todo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private boolean isCompleted;
    @ManyToOne
    @JoinColumn
    private Member member;

    @Builder
    public Todo(Long todoId, String title, String content, boolean isCompleted, Member member){
        this.title = title;
        this.todoId = todoId;
        this.content = content;
        this.isCompleted = false;
        this.member = member;
    }

    public void update(){
        this.isCompleted = !this.isCompleted;
    }
}
