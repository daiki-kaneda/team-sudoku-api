package com.example.team_sudoku_api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // バリデーションエラー
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        log.info("Validation error: {}",e.getMessage());
ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "入力内容に不備があります。"
        );
        problem.setTitle("Validation Failed");

        // どのフィールドがなぜダメなのかをMapに詰める
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        }
        
        // 標準外のフィールドを追加
        problem.setProperty("errors", validationErrors);

        return problem;
    }

    // リクエストのボディ形式のエラー
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleMessageNotReadable(HttpMessageNotReadableException e){
        log.warn("HttpMessage not readable:{}",e.getMessage());
        return ProblemDetail.forStatusAndDetail(
            HttpStatus.BAD_REQUEST,
            "HTTPメッセージが不正確です"
        );
    }

    // データが見つからないとき
    @ExceptionHandler(jakarta.persistence.EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFound(jakarta.persistence.EntityNotFoundException e){
        log.info("Entity not found: {}",e.getMessage());
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
        problem.setTitle("Resource Not Found");
        return problem;
    }

    // 権限不足
    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDenied(AccessDeniedException e){
        log.info("AccessDenied: {}",e.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.FORBIDDEN,
            "この操作を行う権限がありません"
        );
        return problemDetail;
    }

    // 予期せぬシステムエラー
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleAllException(Exception e){
        log.error("Unexpected error:{}",e.getMessage());
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "システムエラーが発生しました。管理者にお問い合わせください。"
        );
        problem.setTitle("Internal server error");

        return problem;
    }
}
