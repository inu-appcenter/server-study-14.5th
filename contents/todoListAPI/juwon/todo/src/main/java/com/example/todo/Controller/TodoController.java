package com.example.todo.Controller;

import com.example.todo.Dto.TodoRequestDto;
import com.example.todo.Dto.TodoResponseDto;
import com.example.todo.Service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@Api(tags = {"Member"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {
	private TodoService todoService;
	
	@PostMapping
	@ApiOperation("todo 생성")
	public Long createTodo(@RequestBody TodoRequestDto todoDTO) {
		return todoService.create(todoDTO);
	}

	@GetMapping("/{todo-id}")
	@ApiOperation("todo id로 조회")
	public TodoResponseDto findTodo(@PathVariable Long id) {
		return todoService.findById(id);
	}

	@PutMapping("/{todo-id}")
	@ApiOperation("todo 정보 수정")
	public void updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto toDoDTO) {
		 todoService.update(id);
	}

	@DeleteMapping("/{todo-id}")
	@ApiOperation("todo 정보 삭제")
	public void deleteTodo(@PathVariable Long id) {
		todoService.delete(id);
	}

}
