package com.gabn.taskmanager.dto.user;

import com.gabn.taskmanager.mocks.UserMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class UserDTOTest {

    @Test
    void userDTOConstructor() {
        assertAll(UserDTO::new);
    }

    @Test
    void userDTOSetter() {
        UserDTO userDTO = UserMock.getUserDTO();
        assertAll(() -> userDTO.setId("nuevoId"));
    }
}