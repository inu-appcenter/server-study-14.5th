package com.appcenter.todolist.domain;

import com.appcenter.todolist.common.Timestamped;
import com.appcenter.todolist.dto.TodoRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
//기본생성자 접근제어를 PROTECTED로 -> @NonNull인 필드 누락 방지
@Entity
@Getter
public class Todo extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long todoId;

    @Length(max = 100)
    @Column(columnDefinition = "varchar(100) not null")
    private String content;

    @Column(columnDefinition = "not null")
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "member_id")
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
