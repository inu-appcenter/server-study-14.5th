package com.appcenter.todolistapi.controller;

import com.appcenter.todolistapi.domain.Member;
import com.appcenter.todolistapi.domain.Todo;
import com.appcenter.todolistapi.dto.MemberRequestDto;
import com.appcenter.todolistapi.dto.MemberResponseDto;
import com.appcenter.todolistapi.dto.TodoResponseDto;
import com.appcenter.todolistapi.repository.MemberRepository;
import com.appcenter.todolistapi.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public void createMember(@RequestBody MemberRequestDto memberRequestDto){
        memberService.createMember(memberRequestDto);
    }

    @GetMapping("/members")
    public List<MemberResponseDto> readAll(){
        return memberService.readAll();
    }

    @GetMapping("/members/{memberId}")
    public MemberResponseDto readMember(@PathVariable Long memberId){
        return memberService.readMember(memberId);
    }


    @PutMapping("/members/{memberId}")
    public void updateMember(@PathVariable Long memberId, @RequestBody MemberRequestDto memberRequestDto){
        memberService.updateMember(memberId, memberRequestDto);
    }

    @DeleteMapping("/members/{memberId}")
    public void deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
    }

}
