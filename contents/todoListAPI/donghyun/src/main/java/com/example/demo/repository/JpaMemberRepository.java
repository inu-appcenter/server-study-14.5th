package com.example.demo.repository;


import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
