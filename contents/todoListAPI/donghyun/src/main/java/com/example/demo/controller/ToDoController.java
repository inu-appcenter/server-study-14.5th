package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.domain.ToDo;
import com.example.demo.dto.todo.ToDoResponseDto;
import com.example.demo.dto.todo.ToDoSaveRequestDto;
import com.example.demo.dto.todo.ToDoUpdateRequestDto;
import com.example.demo.service.ToDoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
@Slf4j
public class ToDoController {

    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    // todo 생성
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @PostMapping
    public Long saveToDo (@RequestBody ToDoSaveRequestDto toDoSaveRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member)authentication.getPrincipal();
        log.info("memberId = {}, memberName = {}, memberPassword = ****", member.getMemberId(), member.getName());
        Long saveToDo = toDoService.saveToDo(member.getMemberId(), toDoSaveRequestDto);

        return saveToDo;
    }

    // todo 목록
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @GetMapping
    public List<ToDoResponseDto> findById () {
        List<ToDo> toDos = toDoService.findToDos();
        List<ToDoResponseDto> toDoResponseDtoList = toDos.stream()
                .map(toDo -> new ToDoResponseDto(toDo))
                .collect(Collectors.toList());

        return toDoResponseDtoList;
    }

    // todo 조회
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/{toDoId}")
    public ToDoResponseDto findById (@PathVariable Long toDoId) {
        ToDo toDo = toDoService.findById(toDoId).get();

        return new ToDoResponseDto(toDo);
    }

    // todo 수정
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @PatchMapping("/{toDoId}")
    public void updateToDo (@PathVariable Long toDoId, @RequestBody ToDoUpdateRequestDto toDoUpdateRequestDto) {
        toDoService.updateToDo(toDoId, toDoUpdateRequestDto);
    }

    // todo 삭제
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token",
                    required = true, dataType = "String", paramType = "header")
    })
    @DeleteMapping("/{toDoId}")
    public void deleteToDo (@PathVariable Long toDoId) {
        toDoService.deleteToDo(toDoId);
    }
}
