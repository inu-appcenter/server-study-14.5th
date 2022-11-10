package com.example.todo.Dto;

import com.example.todo.Domain.Member;
import com.example.todo.Domain.Todo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class MemberResponseDto {

    private Long id;
    @Length(max = 20, message = "이름은 20자 이내로 입력해주세요")
    private String name;

    @Positive(message = "나이를 다시 입력해주세요")
    private int age;

    @Email(message = "이메일 형식에 맞게 입력해주세요")
    private String email;
    private List<Todo> todolist;

    public MemberResponseDto(Member member){
        this.id = member.getId();
        this.name = member.getName();
        this.age = member.getAge();
        this.email = member.getEmail();
        this.todolist = member.getToDoList();
    }
}