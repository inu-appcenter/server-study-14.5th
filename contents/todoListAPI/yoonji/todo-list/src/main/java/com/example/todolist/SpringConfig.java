package com.example.todolist;

import com.example.todolist.repository.*;
import com.example.todolist.service.MemberService;
import com.example.todolist.service.TodoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class SpringConfig {

    private final SpringDataJpaMemberRepository memberRepository;
    private final SpringDataJpaTodoRepository todoRepository;

    public SpringConfig(SpringDataJpaMemberRepository memberRepository, SpringDataJpaTodoRepository todoRepository) {
        this.memberRepository = memberRepository;
        this.todoRepository = todoRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public TodoService todoService() {
        return new TodoService(todoRepository, memberRepository);
    }

}
