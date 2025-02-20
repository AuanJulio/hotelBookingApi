package com.auanjulio.hotelbookingapi.exceptions.handler;

import com.auanjulio.hotelbookingapi.exceptions.EmailAlredyExistsException;
import com.auanjulio.hotelbookingapi.exceptions.model.ApiErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorMessage> genericException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage()).collect(Collectors.toList());

        ApiErrorMessage apiErrorMessage = ApiErrorMessage
                .builder()
                .timestamp(LocalDateTime.now())
                .cdError(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .txStatus(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errors(errors)
                .build();
        return new ResponseEntity<>(apiErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailAlredyExistsException.class)
    public ResponseEntity<ApiErrorMessage> emailAlreadyExistsException(EmailAlredyExistsException ex) {
        ApiErrorMessage apiErrorMessage = ApiErrorMessage
                .builder()
                .timestamp(LocalDateTime.now())
                .cdError(HttpStatus.CONFLICT.value())
                .txStatus(HttpStatus.CONFLICT.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiErrorMessage, HttpStatus.CONFLICT);
    }
}
