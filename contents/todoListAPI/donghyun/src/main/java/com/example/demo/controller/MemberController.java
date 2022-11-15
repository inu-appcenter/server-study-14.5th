package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.dto.member.MemberResponseDto;
import com.example.demo.dto.member.MemberSaveRequestDto;
import com.example.demo.dto.member.MemberUpdateRequestDto;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
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
        return saveMember;
    }

    // 회원 목록
    @GetMapping
    public List<MemberResponseDto> members() {
        List<Member> members = memberService.findMembers();
        List<MemberResponseDto> memberResponseDtoList = members.stream()
                .map(member -> new MemberResponseDto(member))
                .collect(Collectors.toList());

        return memberResponseDtoList;
    }

    // 회원 조회
    @GetMapping("/{memberId}")
    public MemberResponseDto findById (@PathVariable Long memberId) {
        Member member = memberService.findById(memberId).get();

        return new MemberResponseDto(member);
    }

    // 회원 수정
    @PatchMapping("/{memberId}")
    public void updateMember (@PathVariable Long memberId, @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        memberService.updateMember(memberId, memberUpdateRequestDto);
    }

    // 회원 삭제
    @DeleteMapping("/{memberId}")
    public void deleteMember (@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }
}