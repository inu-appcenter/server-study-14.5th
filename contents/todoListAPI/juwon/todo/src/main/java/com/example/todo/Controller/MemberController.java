package com.example.todo.Controller;

import com.example.todo.Dto.MemberRequestDto;
import com.example.todo.Domain.Member;
import com.example.todo.Dto.MemberResponseDto;
import com.example.todo.Service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = {"Member"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private MemberService memberService;

    
    @PostMapping
    @ApiOperation("회원 가입")
    public Long registerMember(@RequestBody MemberRequestDto memberDTO) {
        return memberService.create(memberDTO);
    }

    
    @GetMapping
    @ApiOperation("회원 전체 조회")
    public List<Member> findMembers() {
        return memberService.findMembers();
    }
    
    @GetMapping("/{member-id}")
    @ApiOperation("회원 아이디로 조회")
    public MemberResponseDto findMemberById(@PathVariable Long id) {
        return memberService.findOneId(id);
    }
    
    @PutMapping("/{member-id}")
    @ApiOperation("회원 정보 수정")
    public void updateMember(@PathVariable Long id, @RequestBody MemberRequestDto memberDTO) {
        memberService.update(id, memberDTO);
    }
    
    @DeleteMapping("/{member-id}")
    @ApiOperation("회원 정보 삭제")
    public void deleteMember(@PathVariable Long id) {
        memberService.delete(id);
    }
}
