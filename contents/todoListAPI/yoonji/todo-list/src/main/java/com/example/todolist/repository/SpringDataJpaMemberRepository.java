package com.example.todolist.repository;

import com.example.todolist.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);
}
