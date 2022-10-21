package com.example.demo.repository;

import com.example.demo.domain.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaToDoRepository extends JpaRepository<ToDo, Long> {

}
