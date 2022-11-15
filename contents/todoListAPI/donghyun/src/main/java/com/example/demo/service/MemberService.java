package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.dto.member.MemberSaveRequestDto;
import com.example.demo.dto.member.MemberUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    // 회원 생성
    public Long saveMember(MemberSaveRequestDto memberSaveRequestDto);

    // 회원 목록
    public List<Member> findMembers();

    // 회원 조회
    public Optional<Member> findById(Long memberId);

    // 회원 수정
    public void updateMember(Long memberId, MemberUpdateRequestDto memberUpdateRequestDto);

    // 회원 삭제
    public void deleteMember(Long memberId);
}