package com.example.todo.Dto;

import com.example.todo.Domain.Member;
import com.example.todo.Domain.Todo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@ApiModel(value = "Member 응답")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

    @ApiModelProperty(position = 1, required = true, dataType = "Long", value = "식별자", example = "1")
    private Long id;

    @ApiModelProperty(position = 2, required = true, dataType = "String", value = "이름", example = "이주원")
    private String name;

    @ApiModelProperty(position = 3, required = true, dataType = "int", value = "나이", example = "20")
    private int age;

    @ApiModelProperty(position = 4, required = true, dataType = "String", value = "이메일", example = "example@inu.ac.kr")
    private String email;

    @ApiModelProperty(position = 5, required = true, dataType = "List<Todo>", value = "할 일 목록")
    private List<Todo> todolist;

    public MemberResponseDto(Member member){
        this.id = member.getId();
        this.name = member.getName();
        this.age = member.getAge();
        this.email = member.getEmail();
        this.todolist = member.getToDoList();
    }
}