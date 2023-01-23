package com.gabn.taskmanager.collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class TaskTest {

    @Test
    void taskConstructor() {
        assertAll(Task::new);
    }
}