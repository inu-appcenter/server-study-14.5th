package com.example.todo.Controller;

import com.example.todo.Domain.Todo;
import com.example.todo.Dto.TodoSaveRequestDto;
import com.example.todo.Dto.TodoResponseDto;
import com.example.todo.Dto.TodoUpdateRequestDto;
import com.example.todo.Service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Todo"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {
	private final TodoService todoService;

	@PostMapping("/memberId/{memberId}")
	@ApiImplicitParam(
			name = "memberId"
			, value = "멤버 식별자"
			, required = true
			, dataType = "Long"
			, paramType = "path"
			, defaultValue = "")
	@ApiOperation("todo 생성")
	public Long createTodo(@PathVariable Long memberId, @RequestBody TodoSaveRequestDto todoSaveRequestDto) {
		return todoService.create(memberId, todoSaveRequestDto);
	}

	@GetMapping
	@ApiOperation("todo 전체 조회")
	public List<Todo> findTodos() {
		return todoService.findTodos();
	}

	@GetMapping("/{todoId}")
	@ApiImplicitParam(
			name = "todoId"
			, value = "Todo 식별자"
			, required = true
			, dataType = "Long"
			, paramType = "path"
			, defaultValue = "")
	@ApiOperation("todo id로 조회")
	public TodoResponseDto findTodo(@PathVariable Long todoId) {
		return todoService.findById(todoId);
	}

	@PutMapping("/{todoId}")
	@ApiImplicitParam(
			name = "todoId"
			, value = "Todo 식별자"
			, required = true
			, dataType = "Long"
			, paramType = "path"
			, defaultValue = "")
	@ApiOperation("todo 정보 수정")
	public void updateTodo(@PathVariable Long todoId, @RequestBody TodoUpdateRequestDto todoUpdateRequestDto) {
		todoService.update(todoId, todoUpdateRequestDto);
	}

	@DeleteMapping("/{todoId}")
	@ApiImplicitParam(
			name = "todoId"
			, value = "Todo 식별자"
			, required = true
			, dataType = "Long"
			, paramType = "path"
			, defaultValue = "")
	@ApiOperation("todo 정보 삭제")
	public void deleteTodo(@PathVariable Long todoId) {
		todoService.delete(todoId);
	}

}
