package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.dto.member.MemberSaveRequestDto;
import com.example.demo.dto.member.MemberUpdateRequestDto;
import com.example.demo.repository.JpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private  final JpaMemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(JpaMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 생성
    @Override
    public Long saveMember(MemberSaveRequestDto memberSaveRequestDto) {
        validateDuplicateEmail(memberSaveRequestDto);
        Member member = Member.createMember(memberSaveRequestDto.getEmail(), memberSaveRequestDto.getAge(), memberSaveRequestDto.getName());

        memberRepository.save(member);
        return  member.getId();
    }

    // 회원 이메일 중복 체크
    private void validateDuplicateEmail(MemberSaveRequestDto memberSaveRequestDto) {
        memberRepository.findByEmail(memberSaveRequestDto.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });
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

    // 회원 삭제
    @Override
    public void deleteMember (Long memberId) {
        memberRepository.deleteById(memberId);
    }
}