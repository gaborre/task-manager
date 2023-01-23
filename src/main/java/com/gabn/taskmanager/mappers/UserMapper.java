package com.gabn.taskmanager.mappers;

import com.gabn.taskmanager.collections.User;
import com.gabn.taskmanager.dto.user.CreateUserDTO;
import com.gabn.taskmanager.dto.user.UpdateUserDTO;
import com.gabn.taskmanager.dto.user.UserCriteriaDTO;
import com.gabn.taskmanager.dto.user.UserDTO;
import com.gabn.taskmanager.models.UserModel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.gabn.taskmanager.utils.DateTimeUtils.getLocalDateTimeForCriteria;
import static com.gabn.taskmanager.utils.DateTimeUtils.getStringDateFromLocalDateTime;

@NoArgsConstructor
public final class UserMapper {

    public static UserModel mapCriteriaDTOToModel(UserCriteriaDTO userCriteriaDTO) {
        return UserModel.builder()
            .id(userCriteriaDTO.getId())
            .identification(userCriteriaDTO.getIdentification())
            .name(userCriteriaDTO.getName())
            .lastName(userCriteriaDTO.getLastName())
            .address(userCriteriaDTO.getAddress())
            .phone(userCriteriaDTO.getPhone())
            .createdDate(getLocalDateTimeForCriteria(userCriteriaDTO.getInitDate()))
            .updatedDate(getLocalDateTimeForCriteria(userCriteriaDTO.getEndDate()))
            .build();
    }

    public static UserModel mapCreateDTOToModel(CreateUserDTO createUserDTO) {
        return UserModel.builder()
            .identification(createUserDTO.getIdentification())
            .name(createUserDTO.getName())
            .lastName(createUserDTO.getLastName())
            .address(createUserDTO.getAddress())
            .phone(createUserDTO.getPhone())
            .build();
    }

    public static UserModel mapUpdateDTOToModel(UpdateUserDTO updateUserDTO) {
        return UserModel.builder()
            .id(updateUserDTO.getId())
            .identification(updateUserDTO.getIdentification())
            .name(updateUserDTO.getName())
            .lastName(updateUserDTO.getLastName())
            .address(updateUserDTO.getAddress())
            .phone(updateUserDTO.getPhone())
            .build();
    }

    public static User mapModelToCollectionForCreation(UserModel userModel) {
        return User.builder()
            .identification(userModel.getIdentification())
            .name(userModel.getName())
            .lastName(userModel.getLastName())
            .address(userModel.getAddress())
            .phone(userModel.getPhone())
            .createdDate(LocalDateTime.now(ZoneOffset.UTC))
            .build();
    }

    public static UserModel mapCollectionToModel(User user) {
        return UserModel.builder()
            .id(user.getId())
            .identification(user.getIdentification())
            .name(user.getName())
            .lastName(user.getLastName())
            .address(user.getAddress())
            .phone(user.getPhone())
            .createdDate(user.getCreatedDate())
            .updatedDate(user.getUpdatedDate())
            .build();
    }

    public static UserDTO mapModelToDTO(UserModel userModel) {
        return UserDTO.builder()
            .id(userModel.getId())
            .identification(userModel.getIdentification())
            .name(userModel.getName())
            .lastName(userModel.getLastName())
            .address(userModel.getAddress())
            .phone(userModel.getPhone())
            .createdDate(getStringDateFromLocalDateTime(userModel.getCreatedDate()))
            .updatedDate(getStringDateFromLocalDateTime(userModel.getUpdatedDate()))
            .build();
    }
}
