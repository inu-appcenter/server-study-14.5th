package com.example.todo.Repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.Domain.Todo;
import com.example.todo.Domain.Member;

public interface TodoRepository extends JpaRepository<Todo,Long> {

}
