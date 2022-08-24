package com.musala.dronefleetservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@ResponseBody
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Error handle(DroneNotReadyException exception) {
        log.error(exception.getMessage());
        return error(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Error handle(DroneOverloadedException exception) {
        log.error(exception.getMessage());
        return error(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public Error handle(ConflictException exception) {
        log.error(exception.getMessage());
        return error(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handle(EntityNotFoundException exception) {
        log.error(exception.getMessage());
        return error(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handle(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        return error(exception.getFieldError().getDefaultMessage());
    }

    private Error error(String message) {
        return Error.builder()
                .message(message)
                .build();
    }
}
