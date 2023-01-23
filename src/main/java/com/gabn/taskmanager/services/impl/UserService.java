package com.gabn.taskmanager.services.impl;

import com.gabn.taskmanager.collections.User;
import com.gabn.taskmanager.exceptions.EmptyNotFoundException;
import com.gabn.taskmanager.exceptions.NotFoundException;
import com.gabn.taskmanager.mappers.UserMapper;
import com.gabn.taskmanager.models.UserModel;
import com.gabn.taskmanager.repositories.UserRepository;
import com.gabn.taskmanager.services.IUserService;
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
import static com.gabn.taskmanager.mappers.UserMapper.mapModelToCollectionForCreation;
import static com.gabn.taskmanager.queries.UserQueries.getFindAllQuery;
import static com.gabn.taskmanager.queries.UserQueries.getQueryForUpdate;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public UserService(
        UserRepository userRepository,
        ReactiveMongoTemplate reactiveMongoTemplate
    ) {
        this.userRepository = userRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<UserModel> create(UserModel userModel) {
        return userRepository.save(mapModelToCollectionForCreation(userModel))
            .map(UserMapper::mapCollectionToModel);
    }

    @Override
    public Flux<UserModel> findAll(UserModel userModel) {
        return reactiveMongoTemplate.find(getFindAllQuery(userModel), User.class)
            .map(UserMapper::mapCollectionToModel)
            .switchIfEmpty(Flux.error(new EmptyNotFoundException("User list is empty")));
    }

    @Override
    public Mono<UserModel> findById(String id) {
        return userRepository.findById(id)
            .map(UserMapper::mapCollectionToModel)
            .switchIfEmpty(Mono.error(new NotFoundException("User "+id+" not found")));
    }

    @Override
    public Mono<UserModel> update(UserModel userModel) {
        Map<String, Object> mapForUpdate =
            getQueryForUpdate(userModel);
        return reactiveMongoTemplate.findAndModify(
            ((Query) mapForUpdate.get(QUERY_KEY)),
            ((Update) mapForUpdate.get(UPDATE_KEY)),
            FindAndModifyOptions.options().returnNew(Boolean.TRUE),
            User.class
        ).map(UserMapper::mapCollectionToModel);
    }

    @Override
    public Mono<Void> delete(String id) {
        return userRepository.deleteById(id);
    }
}
