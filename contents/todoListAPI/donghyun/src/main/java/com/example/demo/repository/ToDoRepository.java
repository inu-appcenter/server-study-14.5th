package com.example.demo.repository;


import com.example.demo.domain.ToDo;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository {

    ToDo save(ToDo toDo);
    Optional<ToDo> findById(Long id);
    List<ToDo> findAll();
}
