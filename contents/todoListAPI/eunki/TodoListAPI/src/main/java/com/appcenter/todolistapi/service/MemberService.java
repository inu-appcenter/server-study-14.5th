package com.appcenter.todolistapi.service;

import com.appcenter.todolistapi.domain.Member;
import com.appcenter.todolistapi.domain.Todo;
import com.appcenter.todolistapi.dto.MemberRequestDto;
import com.appcenter.todolistapi.dto.MemberResponseDto;
import com.appcenter.todolistapi.dto.TodoResponseDto;
import com.appcenter.todolistapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
    public List<MemberResponseDto> readAll(){
        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();
        List<Member> memberList = memberRepository.findAll();
        for (Member member : memberList) {
            List<TodoResponseDto> todoResponseDtoList = new ArrayList<>();
            List<Todo> todoList = member.getTodoList();
            for (Todo todo : todoList){
                TodoResponseDto todoResponseDto = new TodoResponseDto(todo);
                todoResponseDtoList.add(todoResponseDto);
            }
            MemberResponseDto memberResponseDto = new MemberResponseDto(member, todoResponseDtoList);
            memberResponseDtoList.add( memberResponseDto );
        }
        return memberResponseDtoList;
    }

    @Transactional
    public MemberResponseDto readMember(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다")
        );
        List<TodoResponseDto> todoResponseDtoList = new ArrayList<>();
        List<Todo> todoList = member.getTodoList();
        for( Todo todo : todoList){
            TodoResponseDto todoResponseDto = new TodoResponseDto(todo);
            todoResponseDtoList.add(todoResponseDto);
        }
        MemberResponseDto memberResponseDto = new MemberResponseDto(member, todoResponseDtoList);
        return memberResponseDto;
    }

    public void updateMember(Long memberId, MemberRequestDto memberRequestDto){
        Member oldMember = memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다")
        );

        oldMember.update(
                memberRequestDto.getName(),
                memberRequestDto.getAge()
        );

        memberRepository.save(oldMember);
    }

    public void deleteMember(Long memberId){
        memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다")
        );
        memberRepository.deleteById(memberId);
    }
}
