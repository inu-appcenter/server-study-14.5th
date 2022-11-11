package com.example.todo.Service;

import com.example.todo.Domain.Todo;
import com.example.todo.Dto.MemberRequestDto;
import com.example.todo.Domain.Member;
import com.example.todo.Dto.MemberResponseDto;
import com.example.todo.Dto.TodoResponseDto;
import com.example.todo.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // member 등록
    public Long create(MemberRequestDto memberDTO) {
        log.info("Member 생성");
        Member member = memberDTO.toEntity();
        log.info("Member 생성 준비");
        memberRepository.save(member);
        log.info("Member 생성 완료 | id = " + member.getId());
        return member.getId();
    }

    // member 전체 조회
    public List<Member> findMembers() {
        log.info("Member 전체 조회 진행");
        return memberRepository.findAll();
    }

    // member 식별자 조회
    public MemberResponseDto findOneId(Long id) {
        log.info("Member id로 조회 | id = " + id);
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다"));
        log.info("Member id로 조회 완료 | id = " + id);
        return new MemberResponseDto(member);
    }

    // member 수정
    public void update(Long id, MemberRequestDto memberDTO) {
        log.info("Member 수정 | id = " + id);
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다"));
        log.info("Member 수정 준비 | id = " + id);
        member.update(memberDTO.getName(), memberDTO.getAge());
        log.info("Member 수정 완료 | id = " + id);
    }

    // member 삭제
    public void delete(Long id) {
        log.info("Member 삭제 | id = " + id);
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다"));
        log.info("Member 삭제 준비 | id = " + id);
        memberRepository.deleteById(id);
        log.info("Member 삭제 완료 | id = " + id);
    }

}
