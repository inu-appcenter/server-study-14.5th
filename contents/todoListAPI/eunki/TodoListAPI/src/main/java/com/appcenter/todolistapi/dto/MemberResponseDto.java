package com.appcenter.todolistapi.dto;

import com.appcenter.todolistapi.domain.Member;
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

    private Long memberId;
    @Length(max = 20, message = "이름을 20자 이하로 적어주세요")
    private String name;
    @Positive(message = "나이를 다시 적어주세요")
    private int age;
    @Email(message = "이메일 형식이 잘못되었습니다")
    private String email;
    private List<TodoResponseDto> todolist;

    public MemberResponseDto(Member member, List<TodoResponseDto> todolist){
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.age = member.getAge();
        this.email = member.getEmail();
        this.todolist = todolist;
    }
}
