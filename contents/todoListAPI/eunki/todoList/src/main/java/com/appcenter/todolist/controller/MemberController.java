package com.appcenter.todolist.controller;

import com.appcenter.todolist.domain.Member;
import com.appcenter.todolist.domain.Todo;
import com.appcenter.todolist.dto.MemberRequestDto;
import com.appcenter.todolist.dto.TodoRequestDto;
import com.appcenter.todolist.repository.MemberRepository;
import com.appcenter.todolist.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {


    private final MemberRepository memberRepository;
    private final MemberService memberService;


    //Member 생성
    @PostMapping("/members")
    public Member createMember(@RequestBody MemberRequestDto memberRequestDto){
        Member member = new Member(memberRequestDto);
        return memberRepository.save(member);
    }

    //Member 전체 조회
    @GetMapping("/members")
    public List<Member> readMemberList() {
        return memberRepository.findAllByOrderByModifiedAtDesc();
    }

    //Member와 해당 Member의 TodoList 조회 - memberId 이용
    @GetMapping("/members/{memberId}")
    public List<Todo> readTodo(@PathVariable Long memberId){
        Member member = memberService.findMember(memberId);
        return member.getTodoList();
    }

    //Member 수정
    @PutMapping("/members/{memberId}")
    public void updateMember(@PathVariable Long memberId, @RequestBody MemberRequestDto memberRequestDto){
        memberService.updateMember(memberId, memberRequestDto);
    }

    //Member 삭제
    @DeleteMapping("/members/{memberId}")
    public void deleteMember(@PathVariable Long memberId){
        memberRepository.deleteById(memberId);
    }

    //Todo 생성
    @PostMapping("/members/{memberId}/todos")
    public void createTodo(@PathVariable Long memberId, @RequestBody TodoRequestDto todoRequestDto){

        Member member = memberService.findMember(memberId);
        Todo todo = new Todo(todoRequestDto);
        member.planTodo(todo);
    }
}
