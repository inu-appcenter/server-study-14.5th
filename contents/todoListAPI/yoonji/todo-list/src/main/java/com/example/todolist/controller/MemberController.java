package com.example.todolist.controller;

import com.example.todolist.domain.Member;
import com.example.todolist.dto.MemberResponseDto;
import com.example.todolist.dto.MemberSaveRequestDto;
import com.example.todolist.dto.MemberUpdateRequestDto;
import com.example.todolist.service.MemberService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //회원 가입
    @PostMapping("/new")
    public Long createMember(@RequestBody MemberSaveRequestDto requestDto) {
        return memberService.join(requestDto);
    }

    //회원 전체 조회
    @GetMapping
    public List<Member> findMembers() {
        return memberService.findMembers();
    }

    //회원 조회
    @GetMapping("/{memberId}")
    public MemberResponseDto findMember(@PathVariable Long memberId) {
        return memberService.findOne(memberId);
    }

    //회원정보 수정
    @PutMapping("/{memberId}")
    public Long updateMember(@PathVariable Long memberId, @RequestBody MemberUpdateRequestDto requestDto) {
        return memberService.update(memberId, requestDto);
    }

    //회원 삭제
    @DeleteMapping("/{memberId}")
    public Long deleteMember(@PathVariable Long memberId) {
        return memberService.delete(memberId);
    }

}
