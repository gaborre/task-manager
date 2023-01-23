package com.gabn.taskmanager.controllers.impl;

import com.gabn.taskmanager.dto.task.CreateTaskDTO;
import com.gabn.taskmanager.dto.task.UpdateTaskDTO;
import com.gabn.taskmanager.exceptions.EmptyNotFoundException;
import com.gabn.taskmanager.exceptions.NotFoundException;
import com.gabn.taskmanager.mocks.TaskMock;
import com.gabn.taskmanager.models.TaskModel;
import com.gabn.taskmanager.services.ITaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "300000")
@AutoConfigureDataMongo
class TaskControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ITaskService taskService;

    @Test
    void create() {
        String uri = "/api/v1/tasks";
        when(taskService.create(any(TaskModel.class)))
            .thenReturn(Mono.just(TaskMock.getTaskModel()));
        webTestClient.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(TaskMock.getCreateTaskDTO()), CreateTaskDTO.class)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isCreated();
    }

    @Test
    void findAll() {
        String uri = "/api/v1/tasks";
        when(taskService.findAll(any(TaskModel.class)))
            .thenReturn(Flux.fromIterable(TaskMock.getTaskModelList()));
        webTestClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk();
    }

    @Test
    void findById() throws NotFoundException {
        String uri = "/api/v1/tasks/1";
        when(taskService.findById(anyString()))
            .thenReturn(Mono.just(TaskMock.getTaskModel()));
        webTestClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk();
    }

    @Test
    void update() {
        String uri = "/api/v1/tasks/1";
        when(taskService.update(any(TaskModel.class)))
            .thenReturn(Mono.just(TaskMock.getTaskModel()));
        webTestClient.put()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(TaskMock.getUpdateTaskDTO()), UpdateTaskDTO.class)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk();
    }

    @Test
    void delete() {
        String uri = "/api/v1/tasks/1";
        when(taskService.delete(anyString()))
            .thenReturn(Mono.empty());
        webTestClient.delete()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk();
    }

    @Test
    void findAllThrowsDateTimeParseException() {
        String uri = "/api/v1/tasks?initDate=2023-01-23&endDate=2023-01-23";
        when(taskService.findAll(any(TaskModel.class)))
            .thenReturn(Flux.fromIterable(TaskMock.getTaskModelList()));
        webTestClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isBadRequest();
    }

    @Test
    void findAllThrowsEmptyNotFoundException() {
        String uri = "/api/v1/tasks";
        when(taskService.findAll(any(TaskModel.class)))
            .thenReturn(Flux.error(new EmptyNotFoundException("Task list is empty")));
        webTestClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void findAllWithDateRange() {
        String uri = "/api/v1/tasks?initDate=2023-01-23T10:00:00.000Z&endDate=2023-01-23T11:00:00.000Z";
        when(taskService.findAll(any(TaskModel.class)))
            .thenReturn(Flux.fromIterable(TaskMock.getTaskModelList()));
        webTestClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk();
    }

    @Test
    void findByIdThrowsNotFoundException() {
        String uri = "/api/v1/tasks/1";
        when(taskService.findById(anyString()))
            .thenReturn(Mono.error(new NotFoundException("Task 1 not found")));
        webTestClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}