package com.example.demo.dto;


import com.example.demo.domain.Member;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSaveRequestDto {

    private Long id;
    private String name;
    private String email;
    private int age;


    public MemberSaveRequestDto(Long id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Member entity() {
        return Member.builder()
                .id(id)
                .name(name)
                .email(email)
                .age(age)
                .build();
    }

}