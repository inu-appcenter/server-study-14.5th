package com.example.todolist.repository;

import com.example.todolist.domain.Member;
import com.example.todolist.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaTodoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findByContent(String content);
}
