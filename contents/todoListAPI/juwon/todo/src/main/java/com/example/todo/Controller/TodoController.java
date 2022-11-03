package com.example.todo.Controller;

import java.util.List;

import com.example.todo.DTO.ToDoDTO;
import com.example.todo.Service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {
	private ToDoService todoService;

	@Autowired
	public TodoController(ToDoService toDoService) {
		this.todoService = toDoService;
	}

	// todo 생성
	@PostMapping("/new-todo")
	public Long createToDo(@RequestBody ToDoDTO todoDTO) {
		return todoService.create(todoDTO);
	}

	// todo 식별자 조회
	@GetMapping("/todos/{todo-id}")
	public ToDoDTO findToDo(@PathVariable Long id) {
		return todoService.findById(id);
	}

	// todo 수정
	@PutMapping("/todos/{todo-id}")
	public Long updateToDo(@PathVariable Long id, @RequestBody ToDoDTO toDoDTO) {
		return todoService.update(id, toDoDTO);
	}

	// todo 삭제
	@DeleteMapping("/todos/{todo-id}")
	public Long deleteTodo(@PathVariable Long id) {
		return todoService.delete(id);
	}

}
