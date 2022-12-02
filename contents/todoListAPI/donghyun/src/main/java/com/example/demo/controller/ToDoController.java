package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.domain.ToDo;
import com.example.demo.dto.ResponseResult;
import com.example.demo.dto.todo.*;
import com.example.demo.service.ToDoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToDoSaveResponse saveToDo (@RequestBody @Validated ToDoSaveRequestDto toDoSaveRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member)authentication.getPrincipal();
        log.info("memberId = {}, memberName = {}, memberPassword = ****", member.getMemberId(), member.getName());
        Long saveToDo = toDoService.saveToDo(member.getMemberId(), toDoSaveRequestDto);

        return new ToDoSaveResponse(saveToDo, true, "todo 생성 완료");
    }

    // todo 목록
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ToDoResponseDto> findById () {
        List<ToDo> toDos = toDoService.findToDos();
        List<ToDoResponseDto> toDoResponseDtoList = toDos.stream()
                .map(toDo -> new ToDoResponseDto(toDo))
                .collect(Collectors.toList());

        Collections.reverse(toDoResponseDtoList);
        return toDoResponseDtoList;
    }

    // todo 조회
    @GetMapping("/{toDoId}")
    @ResponseStatus(HttpStatus.OK)
    public ToDoResponseDto findById (@PathVariable Long toDoId) {
        ToDo toDo = toDoService.findById(toDoId);

        return new ToDoResponseDto(toDo);
    }

    // todo 수정
    @PatchMapping("/{toDoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseResult updateToDo (@PathVariable Long toDoId, @RequestBody @Validated ToDoUpdateRequestDto toDoUpdateRequestDto) {
        toDoService.updateToDo(toDoId, toDoUpdateRequestDto);

        return new ResponseResult(true, "todo 수정 완료");
    }

    // todo 삭제
    @DeleteMapping("/{toDoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseResult deleteToDo (@PathVariable Long toDoId) {
        log.info("[todo 삭제 시작]");
        toDoService.deleteToDo(toDoId);
        log.info("[todo 삭제 완료]");
        return new ResponseResult(true, "todo 삭제 완료");
    }
}
