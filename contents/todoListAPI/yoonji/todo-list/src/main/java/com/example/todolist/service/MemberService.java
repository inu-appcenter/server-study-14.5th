package com.example.todolist.service;

import com.example.todolist.auth.JwtTokenProvider;
import com.example.todolist.domain.Member;
import com.example.todolist.dto.LoginResultDto;
import com.example.todolist.dto.MemberResponseDto;
import com.example.todolist.dto.MemberSaveRequestDto;
import com.example.todolist.dto.MemberUpdateRequestDto;
import com.example.todolist.repository.SpringDataJpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class MemberService {

    private final SpringDataJpaMemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(SpringDataJpaMemberRepository memberRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public Long join(MemberSaveRequestDto requestDto) {
        validateDuplicateEmail(requestDto);
        return memberRepository.save(Member.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .age(requestDto.getAge())
                .name(requestDto.getName())
                .build()).getId();
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

    public LoginResultDto login(String email, String password) throws RuntimeException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new RuntimeException();
        }
        System.out.println(member.getId());
        LoginResultDto loginResultDto = LoginResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(member.getEmail()), member.getRoles()))
                .memberId(member.getId())
                .build();

        return loginResultDto;
    }

}
