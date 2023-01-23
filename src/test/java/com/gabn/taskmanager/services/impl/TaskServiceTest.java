package com.gabn.taskmanager.services.impl;

import com.gabn.taskmanager.collections.Task;
import com.gabn.taskmanager.mocks.TaskMock;
import com.gabn.taskmanager.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Test
    void create() {
        when(taskRepository.save(any(Task.class)))
            .thenReturn(Mono.just(TaskMock.getTask()));
        assertAll(() -> taskService.create(TaskMock.getTaskModelForCreate()));
    }

    @Test
    void findAll() {
        when(reactiveMongoTemplate.find(any(Query.class), any()))
            .thenReturn(Flux.fromIterable(TaskMock.getTaskList()));
        assertAll(() -> taskService.findAll(TaskMock.getTaskModel()));
    }

    @Test
    void findById() {
        String id = "1";
        when(taskRepository.findById(anyString()))
            .thenReturn(Mono.just(TaskMock.getTask()));
        assertAll(() -> taskService.findById(id));
    }

    @Test
    void update() {
        when(reactiveMongoTemplate.findAndModify(any(Query.class), any(Update.class),
            any(FindAndModifyOptions.class), any()))
            .thenReturn(Mono.just(TaskMock.getTask()));
        assertAll(() -> taskService.update(TaskMock.getTaskModelForUpdate()));
    }

    @Test
    void delete() {
        String id = "1";
        when(taskRepository.deleteById(anyString()))
            .thenReturn(Mono.empty());
        assertAll(() -> taskService.delete(id));
    }
}