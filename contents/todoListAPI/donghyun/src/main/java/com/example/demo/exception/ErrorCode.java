package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.CONFLICT;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 404 NOT FOUND
    MEMBER_NOT_FOUND(NOT_FOUND, "존재하지 않는 회원입니다."),
    TODO_NOT_FOUND(NOT_FOUND, "존재하지 않는 todo입니다."),
    // 409 CONFLICT
    DUPLICATE_MEMBER_ID(CONFLICT, "이미 존재하는 아아디입니다."),;

    private final HttpStatus httpStatus;
    private final String message;
}
