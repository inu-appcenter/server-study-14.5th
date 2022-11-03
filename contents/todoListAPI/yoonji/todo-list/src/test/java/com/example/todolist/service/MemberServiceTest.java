package com.example.todolist.service;

import com.example.todolist.domain.Member;
import com.example.todolist.dto.MemberSaveRequestDto;
import com.example.todolist.repository.SpringDataJpaMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired SpringDataJpaMemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        MemberSaveRequestDto member = new MemberSaveRequestDto(0L, "naver.com", 26, "지경");

        Long saveId = memberService.join(member);

        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        MemberSaveRequestDto member1 = new MemberSaveRequestDto(0L, "naver.com", 26, "지경");
        MemberSaveRequestDto member2 = new MemberSaveRequestDto(1L, "naver.com", 26, "지경");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    public void 전체_회원_조회() throws Exception {
        MemberSaveRequestDto member1 = new MemberSaveRequestDto(0L, "gmail.com", 26, "이지경");
        MemberSaveRequestDto member2 = new MemberSaveRequestDto(0L, "naver.com", 26, "저지경");

        memberService.join(member1);
        memberService.join(member2);

        List<Member> memberList = memberService.findMembers();
        for (Member m: memberList){
            System.out.println("[FindSome]: "  + m.getId() + " | " +m.getName());
        }

    }

}