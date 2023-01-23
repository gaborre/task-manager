package com.gabn.taskmanager.queries;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class UserQueriesTest {

    @Test
    void userQueriesConstructor() {
        assertAll(UserQueries::new);
    }
}