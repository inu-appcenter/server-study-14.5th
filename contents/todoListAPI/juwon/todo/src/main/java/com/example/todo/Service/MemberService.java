package com.example.todo.Service;

import com.example.todo.DTO.MemberDTO;
import com.example.todo.Domain.Member;
import com.example.todo.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // member 등록
    public Long register(MemberDTO memberDTO) {
        return memberRepository.save(memberDTO.toEntity()).getId();
    }

    // member 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // member 식별자 조회
    public MemberDTO findOneId(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다"));
        return new MemberDTO(member);
    }

    // member 이메일 조회
    public MemberDTO findOneEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다"));
        return new MemberDTO(member);
    }

    // member 이름 조회
    public MemberDTO findOneName(String name) {
        Member member = memberRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다"));
        return new MemberDTO(member);
    }

    // member 수정
    public Long update(Long id, MemberDTO memberDTO) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다"));
        member.update(memberDTO.getName(), memberDTO.getAge(), memberDTO.getEmail());
        return memberDTO.getId();
    }

    // member 삭제
    public Long delete(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다"));
        memberRepository.deleteById(id);
        return member.getId();
    }

}
