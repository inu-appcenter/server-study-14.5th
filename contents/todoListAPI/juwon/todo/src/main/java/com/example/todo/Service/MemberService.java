package com.example.todo.Service;

import com.example.todo.Dto.*;
import com.example.todo.Domain.Member;
import com.example.todo.Repository.MemberRepository;
import com.example.todo.Auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    /*
    // member 등록
    public Long create(MemberSaveRequestDto memberRequestDto) {
        log.info("Member 생성");
        Member member = memberRequestDto.toEntity();
        isDuplicateEmail(member.getEmail());
        memberRepository.save(member);
        return member.getId();
    }
    */
    public Long register(MemberSaveRequestDto memberSaveRequestDto) {
        Member member = memberSaveRequestDto.toEntity(passwordEncoder.encode(memberSaveRequestDto.getPassword()));
        isDuplicateEmail(member.getEmail());
        memberRepository.save(member);
        return member.getId();
    }

    public MemberLoginResultDto login(MemberLoginRequestDto memberLoginRequestDto) throws RuntimeException {
        Member member = memberRepository.findByEmail(memberLoginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));

        if (!passwordEncoder.matches(memberLoginRequestDto.getPassword(), member.getPassword())) {
            throw new RuntimeException();
        }

        MemberLoginResultDto memberLoginResultDto = MemberLoginResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(member.getEmail()), member.getRoles()))
                .id(member.getId())
                .build();
        return memberLoginResultDto;
    }


    public void isDuplicateEmail(String email) {
        Optional<Member> foundMember = memberRepository.findByEmail(email);
        if(foundMember.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
    }

    // member 전체 조회
    public List<Member> findMembers() {
        log.info("Member 전체 조회 진행");
        return memberRepository.findAll();
    }

    // member 식별자 조회
    public MemberResponseDto findOneId(Long id) {
        log.info("Member id로 조회 | id = " + id);
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다"));
        log.info("Member id로 조회 완료 | id = " + id);
        return new MemberResponseDto(member);
    }

    // member 수정
    public void update(Long id, MemberUpdateRequestDto memberUpdateRequestDto) {
        log.info("Member 수정 | id = " + id);
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다"));
        log.info("Member 수정 준비 | id = " + id);
        member.update(memberUpdateRequestDto.getName(), memberUpdateRequestDto.getAge());
        memberRepository.save(member);
        log.info("Member 수정 완료 | id = " + id);
    }

    // member 삭제
    public void delete(Long id) {
        log.info("Member 삭제 | id = " + id);
        memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다"));
        log.info("Member 삭제 준비 | id = " + id);
        memberRepository.deleteById(id);
        log.info("Member 삭제 완료 | id = " + id);
    }

}
