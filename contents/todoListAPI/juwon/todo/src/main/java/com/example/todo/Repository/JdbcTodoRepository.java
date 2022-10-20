package com.example.todo.Repository;

import java.util.List;
import java.util.Optional;

import com.example.todo.Domain.Member;
import com.example.todo.Domain.ToDo;

public interface JdbcTodoRepository {
	ToDo save(ToDo todo);
	Optional<ToDo> findById(Long id);
	List<ToDo> findAll();
}
