package com.gabn.taskmanager.dto.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class UpdateTaskDTOTest {

    @Test
    void updateTaskDTOConstructor() {
        assertAll(() -> new UpdateTaskDTO("id"));
    }
}