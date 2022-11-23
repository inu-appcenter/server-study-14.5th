package com.appcenter.todolistapi.dto;

import com.appcenter.todolistapi.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class MemberResponseDtoExceptForTodoList {
    private UUID memberId;
    @Length(max = 20, message = "이름을 20자 이하로 적어주세요")
    private String name;
    @Positive(message = "나이를 다시 적어주세요")
    private int age;
    @Email(message = "이메일 형식이 잘못되었습니다")
    private String email;

    public MemberResponseDtoExceptForTodoList(Member member){
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.age = member.getAge();
        this.email = member.getEmail();
    }
}
