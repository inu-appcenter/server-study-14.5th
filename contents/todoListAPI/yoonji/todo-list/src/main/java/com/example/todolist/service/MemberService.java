package com.example.todolist.service;

import com.example.todolist.domain.Member;
import com.example.todolist.dto.MemberResponseDto;
import com.example.todolist.dto.MemberSaveRequestDto;
import com.example.todolist.dto.MemberUpdateRequestDto;
import com.example.todolist.repository.SpringDataJpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final SpringDataJpaMemberRepository memberRepository;

    @Autowired
    public MemberService(SpringDataJpaMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(MemberSaveRequestDto requestDto) {
        validateDuplicateEmail(requestDto);
        return memberRepository.save(requestDto.toEntity()).getId();
    }

    private void validateDuplicateEmail(MemberSaveRequestDto requestDto) {
        memberRepository.findByEmail(requestDto.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public MemberResponseDto findOne(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        return new MemberResponseDto(member);
    }

    public Long update(Long id, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        member.update(requestDto.getAge(), requestDto.getName());

        return memberRepository.save(member).getId();
    }

    public Long delete(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        memberRepository.deleteById(id);

        return member.getId();
    }
}
