package com.example.todolist;

import com.example.todolist.repository.JdbcTemplateMemberRepository;
import com.example.todolist.repository.JdbcTemplateTodoRepository;
import com.example.todolist.repository.MemberRepository;
import com.example.todolist.repository.TodoRepository;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }

    @Bean
    public TodoRepository todoRepository() {
        return new JdbcTemplateTodoRepository(dataSource);
    }
}
