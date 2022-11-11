package com.example.todo.Repository;

import java.util.List;
import java.util.Optional;

import com.example.todo.Domain.Todo;

public interface JdbcTodoRepository {
	Todo save(Todo todo);
	Optional<Todo> findById(Long id);
	List<Todo> findAll();
}
