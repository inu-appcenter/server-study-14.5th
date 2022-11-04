package com.example.todo.Controller;

import com.example.todo.DTO.MemberDTO;
import com.example.todo.Domain.Member;
import com.example.todo.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // member 생성
    @PostMapping("/new-member")
    public Long registerMember(@RequestBody MemberDTO memberDTO) {
        return memberService.register(memberDTO);
    }

    // member 전체 조회
    @GetMapping("/members/")
    public List<Member> findMembers() {
        return memberService.findMembers();
    }

    // member 식별자 조회
    @GetMapping("/members/{member-id}")
    public MemberDTO findMemberById(@PathVariable Long id) {
        return memberService.findOneId(id);
    }

    // member 이메일 조회
    @GetMapping("/members/email/{email}")
    public MemberDTO findMemberByEmail(@PathVariable String email) {
        return memberService.findOneEmail(email);
    }

    // member 이름 조회
    @GetMapping("/members/name/{name}")
    public MemberDTO findMemberByName(@PathVariable String name) {
        return memberService.findOneName(name);
    }

    // member 수정
    @PutMapping("/members/{member-id}")
    public Long updateMember(@PathVariable Long id, @RequestBody MemberDTO memberDTO) {
        return memberService.update(id, memberDTO);
    }

    // member 삭제
    @DeleteMapping("/members/{member-id}")
    public Long deleteMember(@PathVariable Long id) {
        return memberService.delete(id);
    }
}
