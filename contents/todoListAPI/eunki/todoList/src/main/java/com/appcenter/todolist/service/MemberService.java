package com.appcenter.todolist.service;

import com.appcenter.todolist.domain.Member;
import com.appcenter.todolist.domain.Todo;
import com.appcenter.todolist.dto.MemberRequestDto;
import com.appcenter.todolist.dto.TodoRequestDto;
import com.appcenter.todolist.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    //Member 수정, Patch는 나중에 시도
    @Transactional
    public void updateMember( Long memberId, MemberRequestDto memberRequestDto ){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다")
        );
        member.update(memberRequestDto);
    }

}
