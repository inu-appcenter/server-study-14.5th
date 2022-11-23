package com.example.todolist;

import com.example.todolist.auth.JwtTokenProvider;
import com.example.todolist.repository.*;
import com.example.todolist.service.MemberService;
import com.example.todolist.service.TodoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringConfig {

    private final SpringDataJpaMemberRepository memberRepository;
    private final SpringDataJpaTodoRepository todoRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public SpringConfig(SpringDataJpaMemberRepository memberRepository, SpringDataJpaTodoRepository todoRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.todoRepository = todoRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository, jwtTokenProvider, passwordEncoder);
    }

    @Bean
    public TodoService todoService() {
        return new TodoService(todoRepository, memberRepository);
    }

}
