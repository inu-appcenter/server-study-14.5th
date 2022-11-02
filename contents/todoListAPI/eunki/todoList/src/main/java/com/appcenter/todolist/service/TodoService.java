package com.appcenter.todolist.service;

import com.appcenter.todolist.domain.Member;
import com.appcenter.todolist.domain.Todo;
import com.appcenter.todolist.dto.TodoRequestDto;
import com.appcenter.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    //Todo 수정, Patch는 나중에 시도
    @Transactional
    public void updateTodo( Long todoId, TodoRequestDto todoRequestDto ){
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new RuntimeException("목록에 없는 계획입니다")
        );
        todo.update(todoRequestDto);
    }
    //    @PatchMapping(value = "{/todoId}")
    //    public void patchTodo(@PathVariable Long todoId){
    //
    //    }

}
