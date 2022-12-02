package com.example.demo.dto.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ToDoSaveResponse {

    private Long id;

    private Boolean success;

    private String msg;
}
