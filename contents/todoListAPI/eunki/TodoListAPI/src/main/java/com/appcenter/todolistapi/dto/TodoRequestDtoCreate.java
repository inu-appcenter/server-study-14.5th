package com.appcenter.todolistapi.dto;

import com.appcenter.todolistapi.domain.Member;
import com.appcenter.todolistapi.domain.Todo;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class TodoRequestDtoCreate {

    private Long todoId;
    @Length(max = 10)
    private String title;

    @Length(max = 100)
    private String content;

    private boolean isCompleted;
    private Long memberId;

    public TodoRequestDtoCreate(Long todoId, String title, String content, boolean isCompleted){
        this.todoId = todoId;
        this.title = title;
        this.content = content;
        this.isCompleted = isCompleted;
    }

    public Todo toEntity(Member member){
        return Todo.builder()
                .todoId(todoId)
                .title(title)
                .content(content)
                .isCompleted(false)
                .member(member)
                .build();
    }
}
