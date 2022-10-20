package com.example.todo.Repository;

import java.util.List;
import java.util.Optional;

import com.example.todo.Domain.Member;

public interface JdbcMemberRepository {
	Member save(Member member);
	Optional<Member> findById(Long id);
	Optional<Member> findByEmail(String email);
	Optional<Member> findByName(String name);
	List<Member> findAll();
}
