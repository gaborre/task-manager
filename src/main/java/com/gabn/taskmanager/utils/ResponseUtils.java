package com.gabn.taskmanager.utils;

import com.gabn.taskmanager.dto.ResponseDTO;
import org.springframework.http.HttpStatus;

public final class ResponseUtils {

    public static <T> ResponseDTO<T> buildResponseDTO(
        HttpStatus httpStatus,
        T data
    ) {
        return ResponseDTO.<T>builder()
            .code(httpStatus.value())
            .message(httpStatus.getReasonPhrase())
            .data(data)
            .build();
    }
}
