package com.gabn.taskmanager.components;

import com.gabn.taskmanager.dto.ResponseDTO;
import com.gabn.taskmanager.exceptions.EmptyNotFoundException;
import com.gabn.taskmanager.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.format.DateTimeParseException;

import static com.gabn.taskmanager.utils.ResponseUtils.buildResponseDTO;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DateTimeParseException.class)
    public Mono<ResponseEntity<ResponseDTO<Object>>> handleDateTimeParseException(
        DateTimeParseException exception
    ) {
        return Mono.just(
            ResponseEntity.badRequest()
            .body(
                buildResponseDTO(HttpStatus.BAD_REQUEST, exception.getMessage())
            )
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public Mono<ResponseEntity<ResponseDTO<Object>>> handleNotFoundException(
        NotFoundException exception
    ) {
        return Mono.just(
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                    buildResponseDTO(HttpStatus.NOT_FOUND, exception.getMessage())
                )
        );
    }

    @ExceptionHandler(EmptyNotFoundException.class)
    public Mono<ResponseEntity<ResponseDTO<Object>>> handleEmptyNotFoundException(
        EmptyNotFoundException exception
    ) {
        return Mono.just(
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                    buildResponseDTO(HttpStatus.NOT_FOUND, exception.getMessage())
                )
        );
    }
}
