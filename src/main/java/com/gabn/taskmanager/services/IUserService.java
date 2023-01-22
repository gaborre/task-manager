package com.gabn.taskmanager.services;

import com.gabn.taskmanager.models.UserModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {

    Mono<UserModel> create(UserModel userModel);

    Flux<UserModel> findAll(UserModel userModel);

    Mono<UserModel> findById(String id);

    Mono<UserModel> update(UserModel userModel);

    Mono<Void> delete(String id);
}
