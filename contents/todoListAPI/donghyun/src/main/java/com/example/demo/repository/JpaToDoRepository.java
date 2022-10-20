package com.example.demo.repository;

import com.example.demo.domain.ToDo;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaToDoRepository implements  ToDoRepository {

    private EntityManager em;

    public JpaToDoRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public ToDo save(ToDo toDo) {
        em.persist(toDo);
        return toDo;
    }

    @Override
    public Optional<ToDo> findById(Long id) {
        ToDo toDo = em.find(ToDo.class, id);
        return Optional.ofNullable(toDo);
    }

    @Override
    public List<ToDo> findAll() {
        return em.createQuery("select m from ToDo m", ToDo.class)
                .getResultList();
    }
}
