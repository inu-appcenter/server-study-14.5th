package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.dto.MemberSaveRequestDto;
import com.example.demo.dto.MemberUpdateRequestDto;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 생성
    @PostMapping
    public Long saveMember (@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        Long saveMember = memberService.saveMember(memberSaveRequestDto);
        System.out.println("MemberController.saveMember");
        return saveMember;
    }

    // 회원 목록
    @GetMapping
    public List<Member> members() {
        List<Member> members = memberService.findMembers();
        return members;
    }

    // 회원 조회
    @GetMapping("/{member-id}")
    public Optional<Member> findById (@PathVariable Long memberId) {
        Optional<Member> member = memberService.findById(memberId);
        return member;
    }

    // 회원 수정
    @PatchMapping("/{member-id}")
    public void updateMember (@PathVariable Long memberId, @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        memberService.updateMember(memberId, memberUpdateRequestDto);
    }

    // 회원 삭제
    @DeleteMapping("/{member-id}")
    public void deleteMember (@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }
}
