package com.gabn.taskmanager.mocks;

import com.gabn.taskmanager.collections.User;
import com.gabn.taskmanager.dto.user.CreateUserDTO;
import com.gabn.taskmanager.dto.user.UpdateUserDTO;
import com.gabn.taskmanager.dto.user.UserDTO;
import com.gabn.taskmanager.models.UserModel;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public final class UserMock {

    public static User getUser() {
        return User.builder()
            .id("1")
            .identification("1111111")
            .name("Fulano")
            .lastName("De tal")
            .address("Carrera 5 #5-55")
            .phone("3005554433")
            .createdDate(LocalDateTime.now(ZoneOffset.UTC))
            .updatedDate(LocalDateTime.now(ZoneOffset.UTC))
            .build();
    }

    public static UserDTO getUserDTO() {
        return UserDTO.builder()
            .id("1")
            .identification("1111111")
            .name("Fulano")
            .lastName("De tal")
            .address("Carrera 5 #5-55")
            .phone("3005554433")
            .build();
    }

    public static UserModel getUserModel() {
        return UserModel.builder()
            .id("1")
            .identification("1111111")
            .name("Fulano")
            .lastName("De tal")
            .address("Carrera 5 #5-55")
            .phone("3005554433")
            .build();
    }

    public static UserModel getUserModelWithDate() {
        return UserModel.builder()
            .id("1")
            .identification("1111111")
            .name("Fulano")
            .lastName("De tal")
            .address("Carrera 5 #5-55")
            .phone("3005554433")
            .createdDate(LocalDateTime.now(ZoneOffset.UTC))
            .updatedDate(LocalDateTime.now(ZoneOffset.UTC))
            .build();
    }

    public static List<User> getUserList() {
        return List.of(
            getUser()
        );
    }

    public static List<UserModel> getUserModelList() {
        return List.of(
            getUserModel()
        );
    }

    public static UserModel getUserModelForCreate() {
        return UserModel.builder()
            .identification("1111111")
            .name("Fulano")
            .lastName("De tal")
            .address("Carrera 5 #5-55")
            .phone("3005554433")
            .createdDate(LocalDateTime.now(ZoneOffset.UTC))
            .updatedDate(LocalDateTime.now(ZoneOffset.UTC))
            .build();
    }

    public static CreateUserDTO getCreateUserDTO() {
        return CreateUserDTO.builder()
            .identification("1111111")
            .name("Fulano")
            .lastName("De tal")
            .address("Carrera 5 #5-55")
            .phone("3005554433")
            .build();
    }

    public static UpdateUserDTO getUpdateUserDTO() {
        return UpdateUserDTO.builder()
            .id("1")
            .identification("1111111")
            .name("Fulano")
            .lastName("De tal")
            .address("Carrera 5 #5-55")
            .phone("3005554433")
            .build();
    }

    public static UserModel getUserModelForUpdate() {
        return UserModel.builder()
            .id("1")
            .identification("1111111")
            .name("Fulano")
            .lastName("De tal")
            .address("Carrera 5 #5-55")
            .phone("3005554433")
            .createdDate(LocalDateTime.now(ZoneOffset.UTC))
            .updatedDate(LocalDateTime.now(ZoneOffset.UTC))
            .build();
    }
}
