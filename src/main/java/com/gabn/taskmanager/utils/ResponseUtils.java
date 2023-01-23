package com.gabn.taskmanager.utils;

import com.gabn.taskmanager.dto.ResponseDTO;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
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
