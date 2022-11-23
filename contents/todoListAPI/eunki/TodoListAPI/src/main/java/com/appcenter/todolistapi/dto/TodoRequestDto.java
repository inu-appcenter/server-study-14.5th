package com.appcenter.todolistapi.dto;

import com.appcenter.todolistapi.domain.Member;
import com.appcenter.todolistapi.domain.Todo;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.transaction.Transactional;

@Getter
@RequiredArgsConstructor
public class TodoRequestDto {

    private Long todoId;
    @Length(max = 10)
    private String title;

    @Length(max = 100)
    private String content;

    private boolean isCompleted;
    private Long memberId;

    public TodoRequestDto(Long todoId, String title, String content, boolean isCompleted){
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
