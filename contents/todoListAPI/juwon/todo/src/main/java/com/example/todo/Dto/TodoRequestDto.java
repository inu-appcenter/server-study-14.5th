package com.example.todo.Dto;

import com.example.todo.Domain.Member;
import com.example.todo.Domain.Todo;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class TodoRequestDto {
    private Long memberId;
    private Long id;
    @Length(max = 100, message = "내용의 길이는 최대 100글자 입니다")
    private String contents;
    private Boolean isCompleted;



    @Builder
    public TodoRequestDto(Long id, String contents, Boolean isCompleted) {
        this.id = id;
        this.contents = contents;
        this.isCompleted = isCompleted;
    }

    public Todo toEntity(Member member) {
        return Todo.builder()
                .id(id)
                .contents(contents)
                .isCompleted(false)
                .member(member)
                .build();
    }

}
