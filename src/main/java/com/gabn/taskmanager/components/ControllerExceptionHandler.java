package com.gabn.taskmanager.components;

import com.gabn.taskmanager.dto.ResponseDTO;
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
    public Mono<ResponseEntity<ResponseDTO>> handleDateTimeParseException(
        DateTimeParseException exception
    ) {
        return Mono.just(
            ResponseEntity.badRequest()
            .body(
                buildResponseDTO(HttpStatus.BAD_REQUEST, exception.getMessage())
            )
        );
    }
}
