package com.appcenter.todolistapi.controller;

import com.appcenter.todolistapi.dto.MemberRequestDto;
import com.appcenter.todolistapi.dto.MemberResponseDto;
import com.appcenter.todolistapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public void updateMember(@PathVariable UUID memberId, @RequestBody MemberRequestDto memberRequestDto){
        memberService.updateMember(memberId, memberRequestDto);
    }

    @DeleteMapping("/members/{memberId}")
    public void deleteMember(@PathVariable UUID memberId){
        memberService.deleteMember(memberId);
    }

}
