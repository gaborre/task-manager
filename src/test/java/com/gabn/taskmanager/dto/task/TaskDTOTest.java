package com.gabn.taskmanager.dto.task;

import com.gabn.taskmanager.mocks.TaskMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class TaskDTOTest {

    @Test
    void taskDTOConstructor() {
        assertAll(TaskDTO::new);
    }

    @Test
    void taskDTOSetter() {
        TaskDTO taskDTO = TaskMock.getTaskDTO();
        assertAll(() -> taskDTO.setId("nuevoId"));
    }
}