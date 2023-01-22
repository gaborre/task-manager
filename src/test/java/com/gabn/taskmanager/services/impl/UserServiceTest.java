package com.gabn.taskmanager.services.impl;

import com.gabn.taskmanager.collections.User;
import com.gabn.taskmanager.mocks.UserMock;
import com.gabn.taskmanager.repositories.UserRepository;
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
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Test
    void create() {
        when(userRepository.save(any(User.class)))
            .thenReturn(Mono.just(UserMock.getUser()));
        assertAll(() -> userService.create(UserMock.getUserModelForCreate()));
    }

    @Test
    void findAll() {
        when(reactiveMongoTemplate.find(any(Query.class), any()))
            .thenReturn(Flux.fromIterable(UserMock.getUserList()));
        assertAll(() -> userService.findAll(UserMock.getUserModel()));
    }

    @Test
    void findById() {
        String id = "1";
        when(userRepository.findById(anyString()))
            .thenReturn(Mono.just(UserMock.getUser()));
        assertAll(() -> userService.findById(id));
    }

    @Test
    void update() {
        when(reactiveMongoTemplate.findAndModify(any(Query.class), any(Update.class),
            any(FindAndModifyOptions.class), any()))
            .thenReturn(Mono.just(UserMock.getUser()));
        assertAll(() -> userService.update(UserMock.getUserModelForUpdate()));
    }

    @Test
    void delete() {
        String id = "1";
        when(userRepository.deleteById(anyString()))
            .thenReturn(Mono.empty());
        assertAll(() -> userService.delete(id));
    }
}