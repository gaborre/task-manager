package com.gabn.taskmanager.mappers;

import com.gabn.taskmanager.mocks.TaskMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class TaskMapperTest {

    @Test
    void taskMapperConstructor() {
        assertAll(TaskMapper::new);
    }

    @Test
    void mapCollectionToModel() {
        assertAll(() -> TaskMapper.mapCollectionToModel(TaskMock.getTask()));
    }
}