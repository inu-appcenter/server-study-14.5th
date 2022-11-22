package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.dto.member.MemberResponseDto;
import com.example.demo.dto.member.MemberSignInRequestDto;
import com.example.demo.dto.member.MemberSignUpRequestDto;
import com.example.demo.dto.member.MemberUpdateRequestDto;
import com.example.demo.dto.sign.SignInResultDto;
import com.example.demo.dto.sign.SignUpResultDto;
import com.example.demo.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public SignUpResultDto signUp(@RequestBody MemberSignUpRequestDto memberSignUpRequestDto) {
        log.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****, name : {}, role : {}", memberSignUpRequestDto.getMemberId(), memberSignUpRequestDto.getMemberId(), memberSignUpRequestDto.getRole());
        SignUpResultDto signUpResultDto = memberService.signUp(memberSignUpRequestDto);

        if (signUpResultDto.getCode() == 0)
            log.info("[signUp] 회원가입을 완료했습니다. id : {}", memberSignUpRequestDto.getMemberId()); // TODO: 회원 가입 실패할때 로그 처리하기
        return signUpResultDto;
    }

    // 로그인
    @PostMapping("/sign-in")
    public SignUpResultDto signIn(@RequestBody MemberSignInRequestDto memberSignInRequestDto) throws RuntimeException {
        log.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", memberSignInRequestDto.getMemberId());
        SignUpResultDto signUpResultDto = memberService.signIn(memberSignInRequestDto);

        if (signUpResultDto.getCode() == 0) {
            SignInResultDto signInResultDto = (SignInResultDto) memberService.signIn(memberSignInRequestDto);
            log.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}", memberSignInRequestDto.getMemberId(), signInResultDto.getToken());
            return signInResultDto;
        }
        return signUpResultDto;
    }

    @GetMapping("/execption")
    public void exceptionTest() {
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
        HttpHeaders responseHeaders = new HttpHeaders();

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.info("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

    // 회원 목록
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @GetMapping
    public List<MemberResponseDto> members() {
        List<Member> members = memberService.findMembers();
        List<MemberResponseDto> memberResponseDtoList = members.stream()
                .map(member -> new MemberResponseDto(member))
                .collect(Collectors.toList());

        return memberResponseDtoList;
    }

    // 회원 조회
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/{memberId}")
    public MemberResponseDto findById (@PathVariable Long memberId) {
        Member member = memberService.findById(memberId).get();

        return new MemberResponseDto(member);
    }

    // 회원 수정
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @PatchMapping("/{memberId}")
    public void updateMember (@PathVariable Long memberId, @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        memberService.updateMember(memberId, memberUpdateRequestDto);
    }

    // 회원 삭제
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @DeleteMapping("/{memberId}")
    public void deleteMember (@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }
}
