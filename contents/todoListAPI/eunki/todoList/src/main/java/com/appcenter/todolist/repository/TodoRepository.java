package com.appcenter.todolist.repository;

import com.appcenter.todolist.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
