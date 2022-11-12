package com.example.demo.config;

import com.example.demo.repository.JpaMemberRepository;
import com.example.demo.repository.JpaToDoRepository;
import com.example.demo.service.MemberService;
import com.example.demo.service.MemberServiceImpl;
import com.example.demo.service.ToDoService;
import com.example.demo.service.ToDoServiceImpl;
import org.springframework.context.annotation.Bean;

public class Config {

    private final JpaMemberRepository memberRepository;
    private final JpaToDoRepository toDoRepository;

    public Config(JpaMemberRepository memberRepository, JpaToDoRepository toDoRepository) {
        this.memberRepository = memberRepository;
        this.toDoRepository = toDoRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository);
    }

    @Bean
    public ToDoService toDoService() {
        return new ToDoServiceImpl(memberRepository, toDoRepository);
    }
}