package com.appcenter.todolistapi.service;

import com.appcenter.todolistapi.domain.Member;
import com.appcenter.todolistapi.domain.Todo;
import com.appcenter.todolistapi.dto.MemberRequestDto;
import com.appcenter.todolistapi.dto.MemberResponseDto;
import com.appcenter.todolistapi.dto.TodoResponseDtoForMember;
import com.appcenter.todolistapi.repository.MemberRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void createMember(MemberRequestDto memberRequestDto){
        validateEmail(memberRequestDto);
        Member newMember = memberRepository.save(memberRequestDto.toEntity());
    }

    public void validateEmail(MemberRequestDto memberRequestDto){
        memberRepository.findByEmail(memberRequestDto.getEmail())
                .ifPresent(a -> {
                    throw new RuntimeException("이미 사용중인 이메일입니다");
                });
    }

    @Transactional
    @JsonIgnore
    public List<MemberResponseDto> readAll(){
        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();
        List<Member> memberList = memberRepository.findAll();
        for (Member member : memberList) {
            List<TodoResponseDtoForMember> todoResponseDtoForMemberList = new ArrayList<>();
            //@JsonIgnore을 쓰니 필요 없어졌당..?
//            List<Todo> todoList = member.getTodoList();
//            for (Todo todo : todoList){
//                TodoResponseDtoForMember todoResponseDtoForMember = new TodoResponseDtoForMember(todo);
//                todoResponseDtoForMemberList.add(todoResponseDtoForMember);
//            }
            MemberResponseDto memberResponseDto = new MemberResponseDto(member, todoResponseDtoForMemberList);
            memberResponseDtoList.add( memberResponseDto );
        }
        return memberResponseDtoList;
    }

    @Transactional
    public MemberResponseDto readMember(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다")
        );
        List<TodoResponseDtoForMember> todoResponseDtoForMemberList = new ArrayList<>();
        List<Todo> todoList = member.getTodoList();
        for( Todo todo : todoList){
            TodoResponseDtoForMember todoResponseDtoForMember = new TodoResponseDtoForMember(todo);
            todoResponseDtoForMemberList.add(todoResponseDtoForMember);
        }
        MemberResponseDto memberResponseDto = new MemberResponseDto(member, todoResponseDtoForMemberList);
        return memberResponseDto;
    }

    public void updateMember(UUID memberId, MemberRequestDto memberRequestDto){
        Member oldMember = memberRepository.findByMemberId(memberId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다")
        );

        oldMember.update(
                memberRequestDto.getName(),
                memberRequestDto.getAge()
        );

        memberRepository.save(oldMember);
    }

    public void deleteMember(UUID memberId){
        memberRepository.findByMemberId(memberId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다")
        );
        memberRepository.deleteMemberByMemberId(memberId);
    }
}
