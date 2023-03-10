package com.gabn.taskmanager.controllers.impl;

import com.gabn.taskmanager.dto.user.CreateUserDTO;
import com.gabn.taskmanager.dto.user.UpdateUserDTO;
import com.gabn.taskmanager.exceptions.EmptyNotFoundException;
import com.gabn.taskmanager.exceptions.NotFoundException;
import com.gabn.taskmanager.mocks.TaskMock;
import com.gabn.taskmanager.mocks.UserMock;
import com.gabn.taskmanager.models.TaskModel;
import com.gabn.taskmanager.models.UserModel;
import com.gabn.taskmanager.services.IUserService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "300000")
@AutoConfigureDataMongo
class UserControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private IUserService userService;

    @Test
    void create() {
        String uri = "/api/v1/users";
        when(userService.create(any(UserModel.class)))
            .thenReturn(Mono.just(UserMock.getUserModel()));
        webTestClient.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(UserMock.getCreateUserDTO()), CreateUserDTO.class)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isCreated();
    }

    @Test
    void findAll() {
        String uri = "/api/v1/users";
        when(userService.findAll(any(UserModel.class)))
            .thenReturn(Flux.fromIterable(UserMock.getUserModelList()));
        webTestClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk();
    }

    @Test
    void findById() {
        String uri = "/api/v1/users/1";
        when(userService.findById(anyString()))
            .thenReturn(Mono.just(UserMock.getUserModel()));
        webTestClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk();
    }

    @Test
    void update() {
        String uri = "/api/v1/users/1";
        when(userService.update(any(UserModel.class)))
            .thenReturn(Mono.just(UserMock.getUserModel()));
        webTestClient.put()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(UserMock.getUpdateUserDTO()), UpdateUserDTO.class)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk();
    }

    @Test
    void delete() {
        String uri = "/api/v1/users/1";
        when(userService.delete(anyString()))
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
        String uri = "/api/v1/users?initDate=2023-01-23&endDate=2023-01-23";
        when(userService.findAll(any(UserModel.class)))
            .thenReturn(Flux.fromIterable(UserMock.getUserModelList()));
        webTestClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isBadRequest();
    }

    @Test
    void findAllThrowsEmptyNotFoundException() {
        String uri = "/api/v1/users";
        when(userService.findAll(any(UserModel.class)))
            .thenReturn(Flux.error(new EmptyNotFoundException("User list is empty")));
        webTestClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void findAllWithDateRange() {
        String uri = "/api/v1/users?initDate=2023-01-23T10:00:00.000Z&endDate=2023-01-23T11:00:00.000Z";
        when(userService.findAll(any(UserModel.class)))
            .thenReturn(Flux.fromIterable(UserMock.getUserModelList()));
        webTestClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk();
    }

    @Test
    void findByIdThrowsNotFoundException() {
        String uri = "/api/v1/users/1";
        when(userService.findById(anyString()))
            .thenReturn(Mono.error(new NotFoundException("User 1 not found")));
        webTestClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}