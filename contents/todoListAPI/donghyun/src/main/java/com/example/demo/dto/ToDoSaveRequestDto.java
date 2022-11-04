package com.example.demo.dto;


import com.example.demo.domain.ToDo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDoSaveRequestDto {

    private Long id;
    private String content;
    private boolean isCompleted;

    public ToDoSaveRequestDto(Long id, String content, boolean isCompleted) {
        this.id = id;
        this.content = content;
        this.isCompleted = isCompleted;
    }

    public ToDo entity() {
        return ToDo.builder()
                .id(id)
                .content(content)
                .isCompleted(isCompleted)
                .build();
    }
}