package com.example.demo.exception;

import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ExceptionResponse> CustomExceptionHandler(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        log.error("CustomExceptionHandler 호출 {}, {}", errorCode.getHttpStatus(), errorCode.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(false, errorCode.getMessage());

        return new ResponseEntity<>(exceptionResponse, errorCode.getHttpStatus());
    }

    @ExceptionHandler(value = JsonParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse JsonExceptionHandler(JsonParseException e) {
        log.error("JsonExceptionHandler 호출 {}, {}", e.getCause(), e.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(false, "입력 데이터의 타입이 일치하지 않습니다.");

        return exceptionResponse;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse MethodExceptionHandler(MethodArgumentNotValidException e) {
        log.error("MethodException 호출 {}, {}", e.getCause(), e.getMessage());
        FieldError fieldError = e.getBindingResult().getFieldError();
        ExceptionResponse exceptionResponse = new ExceptionResponse(false, fieldError.getDefaultMessage());

        return exceptionResponse;
    }
}
