package com.appcenter.todolistapi.repository;

import com.appcenter.todolistapi.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
