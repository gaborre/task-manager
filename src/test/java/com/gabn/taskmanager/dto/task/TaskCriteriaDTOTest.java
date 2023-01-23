package com.gabn.taskmanager.dto.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class TaskCriteriaDTOTest {

    @Test
    void builder() {
        assertAll(() -> TaskCriteriaDTO.builder().build());
    }

    @Test
    void taskCriteriaDTOAllArgsConstructor() {
        assertAll(
            () -> new TaskCriteriaDTO(
                "id", "name", "description",
                "2023-01-23T10:00:00.000Z", "2023-01-23T10:00:00.000Z"
            )
        );
    }
}