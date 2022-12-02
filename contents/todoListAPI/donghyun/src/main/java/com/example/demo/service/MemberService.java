package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.dto.member.MemberSignInRequestDto;
import com.example.demo.dto.member.MemberSignUpRequestDto;
import com.example.demo.dto.member.MemberUpdateRequestDto;
import com.example.demo.dto.sign.SignInResultDto;
import com.example.demo.dto.sign.SignUpResultDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    // 회원 가입
    public SignUpResultDto signUp(MemberSignUpRequestDto memberSignUpRequestDto);

    // 로그인
    public SignInResultDto signIn(MemberSignInRequestDto memberSignInRequestDto) throws RuntimeException;

    // 회원 목록
    public List<Member> findMembers();

    // 회원 조회
    public Member findById(Long memberId);

    // 회원 수정
    public void updateMember(Long memberId, MemberUpdateRequestDto memberUpdateRequestDto);

    // 회원 삭제
    public void deleteMember(Long memberId);
}
