package com.example.todolist.controller;

import com.example.todolist.domain.Member;
import com.example.todolist.dto.MemberResponseDto;
import com.example.todolist.dto.MemberSaveRequestDto;
import com.example.todolist.dto.MemberUpdateRequestDto;
import com.example.todolist.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Member"})
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ApiOperation("회원 가입")
    @PostMapping("/new")
    public Long createMember(@RequestBody MemberSaveRequestDto requestDto) {
        return memberService.join(requestDto);
    }

    @ApiOperation("회원 전체 조회")
    @GetMapping
    public List<Member> findMembers() {
        return memberService.findMembers();
    }

    @ApiOperation("회원 조회")
    @GetMapping("/{memberId}")
    public MemberResponseDto findMember(@PathVariable Long memberId) {
        return memberService.findOne(memberId);
    }

    @ApiOperation("회원 정보 수정")
    @PutMapping("/{memberId}")
    public Long updateMember(@PathVariable Long memberId, @RequestBody MemberUpdateRequestDto requestDto) {
        return memberService.update(memberId, requestDto);
    }

    @ApiOperation("회원 삭제")
    @DeleteMapping("/{memberId}")
    public Long deleteMember(@PathVariable Long memberId) {
        return memberService.delete(memberId);
    }

}
