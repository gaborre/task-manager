package com.gabn.taskmanager.services.impl;

import com.gabn.taskmanager.collections.Task;
import com.gabn.taskmanager.exceptions.EmptyNotFoundException;
import com.gabn.taskmanager.exceptions.NotFoundException;
import com.gabn.taskmanager.mappers.TaskMapper;
import com.gabn.taskmanager.models.TaskModel;
import com.gabn.taskmanager.repositories.TaskRepository;
import com.gabn.taskmanager.services.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

import static com.gabn.taskmanager.constants.GeneralConstants.QUERY_KEY;
import static com.gabn.taskmanager.constants.GeneralConstants.UPDATE_KEY;
import static com.gabn.taskmanager.mappers.TaskMapper.mapModelToCollectionForCreation;
import static com.gabn.taskmanager.queries.TaskQueries.getFindAllQuery;
import static com.gabn.taskmanager.queries.TaskQueries.getQueryForUpdate;

@Service
@Slf4j
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public TaskService(
        TaskRepository taskRepository,
        ReactiveMongoTemplate reactiveMongoTemplate
    ) {
        this.taskRepository = taskRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<TaskModel> create(TaskModel userModel) {
        return taskRepository.save(mapModelToCollectionForCreation(userModel))
            .map(TaskMapper::mapCollectionToModel);
    }

    @Override
    public Flux<TaskModel> findAll(TaskModel userModel) {
        return reactiveMongoTemplate.find(getFindAllQuery(userModel), Task.class)
            .map(TaskMapper::mapCollectionToModel)
            .switchIfEmpty(Flux.error(new EmptyNotFoundException("Task list is empty")));
    }

    @Override
    public Mono<TaskModel> findById(String id) {
        return taskRepository.findById(id)
            .map(TaskMapper::mapCollectionToModel)
            .switchIfEmpty(Mono.error(new NotFoundException("Task "+id+" not found")));
    }

    @Override
    public Mono<TaskModel> update(TaskModel taskModel) {
        Map<String, Object> mapForUpdate =
            getQueryForUpdate(taskModel);
        return reactiveMongoTemplate.findAndModify(
            ((Query) mapForUpdate.get(QUERY_KEY)),
            ((Update) mapForUpdate.get(UPDATE_KEY)),
            FindAndModifyOptions.options().returnNew(Boolean.TRUE),
            Task.class
        ).map(TaskMapper::mapCollectionToModel);
    }

    @Override
    public Mono<Void> delete(String id) {
        return taskRepository.deleteById(id);
    }
}
