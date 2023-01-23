package com.gabn.taskmanager.dto.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class UserCriteriaDTOTest {

    @Test
    void builder() {
        assertAll(() -> UserCriteriaDTO.builder().build());
    }

    @Test
    void userCriteriaDTOAllArgsConstructor() {
        assertAll(
            () -> new UserCriteriaDTO(
                "id", "identification", "name",
                "lastName", "address", "phone",
                "2023-01-23T10:00:00.000Z", "2023-01-23T10:00:00.000Z"
            )
        );
    }
}