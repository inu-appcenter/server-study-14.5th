package com.example.todo.Controller;

import com.example.todo.Dto.*;
import com.example.todo.Domain.Member;
import com.example.todo.Service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"Member"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    /*
    @PostMapping
    @ApiOperation("회원 가입")
    public Long registerMember(@RequestBody @Valid MemberSaveRequestDto memberSaveRequestDto) {
        return memberService.create(memberSaveRequestDto);
    }*/

    @PostMapping("/register")
    @ApiOperation("회원 가입")
    public Long register(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        return memberService.register(memberSaveRequestDto);
    }

    @PostMapping("/login")
    @ApiOperation("로그인")
    public MemberLoginResultDto login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        return memberService.login(memberLoginRequestDto);
    }

    @GetMapping
    @ApiOperation("회원 전체 조회")
    public List<MemberResponseDto> findMembers() {
        List<Member> members = memberService.findMembers();
        List<MemberResponseDto> memberResponseDtoList = members.stream()
                .map(member -> new MemberResponseDto(member))
                .collect(Collectors.toList());

        return memberResponseDtoList;
    }

    @GetMapping("/{memberId}")
    @ApiImplicitParam(
            name = "memberId"
            , value = "멤버 식별자"
            , required = true
            , dataType = "Long"
            , paramType = "path"
            , defaultValue = "")
    @ApiOperation("회원 아이디로 조회")
    public MemberResponseDto findMemberById(@PathVariable Long memberId) {
        return memberService.findOneId(memberId);
    }

    @PutMapping("/{memberId}")
    @ApiImplicitParam(
            name = "memberId"
            , value = "멤버 식별자"
            , required = true
            , dataType = "Long"
            , paramType = "path"
            , defaultValue = "")
    @ApiOperation("회원 정보 수정")
    public void updateMember(@PathVariable Long memberId, @RequestBody @Valid MemberUpdateRequestDto memberUpdateRequestDto) {
        memberService.update(memberId, memberUpdateRequestDto);
    }

    @DeleteMapping("/{memberId}")
    @ApiImplicitParam(
            name = "memberId"
            , value = "멤버 식별자"
            , required = true
            , dataType = "Long"
            , paramType = "path"
            , defaultValue = "")
    @ApiOperation("회원 정보 삭제")
    public void deleteMember(@PathVariable Long memberId) {
        memberService.delete(memberId);
    }
}
