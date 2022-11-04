package com.appcenter.todolist.repository;

import com.appcenter.todolist.domain.Member;
import com.appcenter.todolist.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByOrderByModifiedAtDesc();

}
