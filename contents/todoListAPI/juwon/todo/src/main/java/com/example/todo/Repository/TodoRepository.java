package com.example.todo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.Domain.Member;

public interface TodoRepository extends JpaRepository<Member,Long> {
	Optional<Member> findByEmail(String email);
}