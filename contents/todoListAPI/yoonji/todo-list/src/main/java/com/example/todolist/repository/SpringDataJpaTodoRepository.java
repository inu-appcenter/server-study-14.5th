package com.example.todolist.repository;

import com.example.todolist.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaTodoRepository extends JpaRepository<Todo, Long> {

}
