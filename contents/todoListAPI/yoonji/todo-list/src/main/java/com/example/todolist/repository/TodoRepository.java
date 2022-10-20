package com.example.todolist.repository;

import com.example.todolist.domain.Member;
import com.example.todolist.domain.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    Todo save(Todo todo);
    Optional<Todo> findById(Long id);
    List<Todo> findAll();
}
