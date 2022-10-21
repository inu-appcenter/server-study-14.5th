package com.example.todo.Controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.Domain.Member;
import com.example.todo.Repository.TodoRepository;

@RestController
public class TodoController {
	
	
	
	 @PersistenceContext // or even @Autowired
	 private EntityManager em;
	 
	
	 @Autowired
	 TodoRepository repo;
	 
	 
	@GetMapping("/create")
	List<Member> hello() {

		Member mem = new Member();
		mem.setName("juwon");
		mem.setAge(20);
		mem.setCreatedAt(LocalDateTime.now());
		mem.setUpdatedAt(LocalDateTime.now());
		mem.setEmail("juwon@juwon.com");
		repo.save(mem);
		

		Member mem2 = new Member();
		mem2.setName("null member");
		repo.save(mem2);
		return repo.findAll();
	}
}
