package com.gabn.taskmanager.services;

import com.gabn.taskmanager.models.TaskModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITaskService {

    Mono<TaskModel> create(TaskModel taskModel);

    Flux<TaskModel> findAll(TaskModel taskModel);

    Mono<TaskModel> findById(String id);

    Mono<TaskModel> update(TaskModel taskModel);

    Mono<Void> delete(String id);
}
