package com.example.demo.service;

import com.example.demo.config.security.JwtTokenProvider;
import com.example.demo.config.security.common.CommonResponse;
import com.example.demo.domain.Member;
import com.example.demo.dto.member.MemberSignInRequestDto;
import com.example.demo.dto.member.MemberSignUpRequestDto;
import com.example.demo.dto.member.MemberUpdateRequestDto;
import com.example.demo.dto.sign.SignInResultDto;
import com.example.demo.dto.sign.SignUpResultDto;
import com.example.demo.repository.JpaMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final JpaMemberRepository memberRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(JpaMemberRepository memberRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원 생성
    @Override
    public SignUpResultDto signUp(MemberSignUpRequestDto memberSignUpRequestDto) {

        SignUpResultDto signUpResultDto = new SignUpResultDto();

        log.info("[아이디 중복 검사]");
        if (validateDuplicateMemberId(memberSignUpRequestDto)) {
            setFailResult(signUpResultDto);
            return signUpResultDto;
        }
        log.info("[아이디 중복 검사 통과]");
        Member member;
        if (memberSignUpRequestDto.getRole().equalsIgnoreCase("admin")) {
            member = Member.builder()
                    .memberId(memberSignUpRequestDto.getMemberId())
                    .password(passwordEncoder.encode(memberSignUpRequestDto.getPassword()))
                    .name(memberSignUpRequestDto.getName())
                    .email(memberSignUpRequestDto.getEmail())
                    .age(memberSignUpRequestDto.getAge())
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        } else {
            member = Member.builder()
                    .memberId(memberSignUpRequestDto.getMemberId())
                    .password(passwordEncoder.encode(memberSignUpRequestDto.getPassword()))
                    .name(memberSignUpRequestDto.getName())
                    .email(memberSignUpRequestDto.getEmail())
                    .age(memberSignUpRequestDto.getAge())
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }

        Member savedMember = memberRepository.save(member);

        if (!savedMember.getName().isEmpty()) {
            setSuccessResult(signUpResultDto);
        } else {
            setFailResult(signUpResultDto);
        }

        return signUpResultDto;
    }

    // 회원 아이디 중복 체크
    private boolean validateDuplicateMemberId(MemberSignUpRequestDto memberSignUpRequestDto) {
        return memberRepository.existsByMemberId(memberSignUpRequestDto.getMemberId());
    }

    // 로그인
    @Override
    public SignUpResultDto signIn(MemberSignInRequestDto memberSignInRequestDto) throws RuntimeException {

        SignUpResultDto signUpResultDto = new SignUpResultDto();
        if (!memberRepository.existsByMemberId(memberSignInRequestDto.getMemberId())) {
            setFailResult(signUpResultDto);
            return signUpResultDto;
        }
        log.info("[getSignInResult] signDataHandler 로 회원 정보 요청");
        Member member = memberRepository.getByMemberId(memberSignInRequestDto.getMemberId()).get();
        log.info("[getSignInResult] Id : {}", memberSignInRequestDto.getMemberId());

        log.info("[getSignInResult] 패스워드 비교 수행");
        if (!passwordEncoder.matches(memberSignInRequestDto.getPassword(), member.getPassword())) {
            setFailResult(signUpResultDto);
            return signUpResultDto;
        }
        log.info("[getSignInResult] 패스워드 일치");

        log.info("[getSignInResult] SignInResultDto 객체 생성");
        SignInResultDto signInResultDto = SignInResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(member.getMemberId()), member.getRoles()))
                .build();
        log.info("[getSignInResult] SignInResultDto 객체에 값 주입");
        setSuccessResult(signInResultDto);

        return signInResultDto;
    }

    private void setSuccessResult(SignUpResultDto results) {
        results.setSuccess(true);
        results.setCode(CommonResponse.SUCCESS.getCode());
        results.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDto results) {
        results.setSuccess(false);
        results.setCode(CommonResponse.FAIL.getCode());
        results.setMsg(CommonResponse.FAIL.getMsg());
    }

    // 회원 목록
    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 조회
    @Override
    public Optional<Member> findById(Long memberId) {
        return  memberRepository.findById(memberId);
    }

    // 회원 수정
    @Override
    public void updateMember(Long memberId, MemberUpdateRequestDto memberUpdateRequestDto) {
        Member member = memberRepository.findById(memberId).get();
        member.setAge(memberUpdateRequestDto.getAge());
        member.setName(memberUpdateRequestDto.getName());

        memberRepository.save(member);
    }

    // 회원 탈퇴
    @Override
    public void deleteMember (Long memberId) {
        memberRepository.deleteById(memberId);
    }
}