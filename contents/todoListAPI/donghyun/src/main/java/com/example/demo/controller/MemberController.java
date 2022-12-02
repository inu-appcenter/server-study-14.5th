package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.dto.ResponseResult;
import com.example.demo.dto.member.MemberResponseDto;
import com.example.demo.dto.member.MemberSignInRequestDto;
import com.example.demo.dto.member.MemberSignUpRequestDto;
import com.example.demo.dto.member.MemberUpdateRequestDto;
import com.example.demo.dto.sign.SignInResultDto;
import com.example.demo.dto.sign.SignUpResultDto;
import com.example.demo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 가입
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResultDto signUp(@RequestBody @Validated MemberSignUpRequestDto memberSignUpRequestDto) {
        log.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****, name : {}, role : {}", memberSignUpRequestDto.getMemberId(), memberSignUpRequestDto.getMemberId(), memberSignUpRequestDto.getRole());
        SignUpResultDto signUpResultDto = memberService.signUp(memberSignUpRequestDto);
        log.info("[signUp] 회원가입을 완료했습니다. id : {}", memberSignUpRequestDto.getMemberId());

        return signUpResultDto;
    }

    // 로그인
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public SignInResultDto signIn(@RequestBody @Validated MemberSignInRequestDto memberSignInRequestDto) {
        log.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", memberSignInRequestDto.getMemberId());
        SignInResultDto signInResultDto = memberService.signIn(memberSignInRequestDto);

        if (signInResultDto.getSuccess())
            log.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}", memberSignInRequestDto.getMemberId(), signInResultDto.getToken());

        return signInResultDto;
    }

    // 회원 목록
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MemberResponseDto> members() {
        List<Member> members = memberService.findMembers();
        List<MemberResponseDto> memberResponseDtoList = members.stream()
                .map(member -> new MemberResponseDto(member))
                .collect(Collectors.toList());

        Collections.reverse(memberResponseDtoList);
        return memberResponseDtoList;
    }

    // 회원 조회
    @GetMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto findById (@PathVariable Long memberId) {
        Member member = memberService.findById(memberId);

        return new MemberResponseDto(member);
    }

    // 회원 수정
    @PatchMapping("/{memberId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseResult updateMember (@PathVariable Long memberId, @RequestBody @Validated MemberUpdateRequestDto memberUpdateRequestDto) {
        log.info("[회원 수정 시작]");
        memberService.updateMember(memberId, memberUpdateRequestDto);
        log.info("[회원 수정 완료]");
        return new ResponseResult(true, "회원 수정 완료");
    }

    // 회원 삭제
    @DeleteMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseResult deleteMember (@PathVariable Long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseResult(true, "회원 삭제 완료");
    }
}
