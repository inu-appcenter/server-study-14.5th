package com.example.todo.DTO;

import com.example.todo.Domain.Member;
import com.example.todo.Domain.ToDo;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ToDoDTO {
    private Long id;
    private Long memberId;
    private String contents;
    private Boolean isCompleted;
    private Member member;


    @Builder
    public ToDoDTO(ToDo todo) {
        this.contents = todo.getContents();
        this.isCompleted = todo.getIsCompleted();
        this.member = todo.getMember();
    }

    public ToDo toEntity(Member member) {
        return ToDo.builder()
                .id(id)
                .contents(contents)
                .isCompleted(isCompleted)
                .member(member)
                .build();
    }

}
