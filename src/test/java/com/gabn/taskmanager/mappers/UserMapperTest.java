package com.gabn.taskmanager.mappers;

import com.gabn.taskmanager.mocks.UserMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @Test
    void userMapperConstructor() {
        assertAll(UserMapper::new);
    }

    @Test
    void mapCollectionToModel() {
        assertAll(() -> UserMapper.mapCollectionToModel(UserMock.getUser()));
    }
}