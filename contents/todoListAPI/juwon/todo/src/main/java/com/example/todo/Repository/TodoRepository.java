package com.example.todo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.Domain.Member;
import com.example.todo.Domain.ToDo;

@Repository
public interface TodoRepository extends JpaRepository<Member,Long> {
	ToDo save(ToDo todo);
	Optional<Member> findById(Long id);
	Optional<Member> findByEmail(String email);
}
