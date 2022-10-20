package com.example.todolist.dbtest;

import com.example.todolist.domain.Member;
import com.example.todolist.repository.SpringDataJpaMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class JpaTest {
    @Autowired
    SpringDataJpaMemberRepository springDataJpaMemberRepository;

    @Test
    public void save() {
        Member member = new Member();
        member.setName("ji kyeong");
        springDataJpaMemberRepository.save(member);

        //Member result = springDataJpaMemberRepository.findById(member.getId()).get();
        //assertThat(result).isEqualTo(member);
    }

    @Test
    void findAllTest() {
        List<Member> memberList = springDataJpaMemberRepository.findAll();
        for (Member m: memberList){
            System.out.println("[FindSome]: "  + m.getId() + " | " +m.getName());
        }
    }

}
