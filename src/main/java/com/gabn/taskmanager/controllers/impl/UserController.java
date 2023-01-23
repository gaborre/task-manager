package com.gabn.taskmanager.controllers.impl;

import com.gabn.taskmanager.controllers.IUserController;
import com.gabn.taskmanager.dto.ResponseDTO;
import com.gabn.taskmanager.dto.user.CreateUserDTO;
import com.gabn.taskmanager.dto.user.UpdateUserDTO;
import com.gabn.taskmanager.dto.user.UserCriteriaDTO;
import com.gabn.taskmanager.dto.user.UserDTO;
import com.gabn.taskmanager.mappers.UserMapper;
import com.gabn.taskmanager.models.UserModel;
import com.gabn.taskmanager.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

import static com.gabn.taskmanager.mappers.UserMapper.mapCreateDTOToModel;
import static com.gabn.taskmanager.mappers.UserMapper.mapCriteriaDTOToModel;
import static com.gabn.taskmanager.mappers.UserMapper.mapModelToDTO;
import static com.gabn.taskmanager.mappers.UserMapper.mapUpdateDTOToModel;
import static com.gabn.taskmanager.utils.ResponseUtils.buildResponseDTO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
    value = "/api/v1/users",
    produces = APPLICATION_JSON_VALUE
)
public class UserController implements IUserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping(
        consumes = APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<ResponseDTO<UserDTO>>> create(CreateUserDTO createUserDTO) {
        return userService.create(mapCreateDTOToModel(createUserDTO))
            .map((UserModel userModel) -> ResponseEntity.status(HttpStatus.CREATED)
                .body(
                    buildResponseDTO(
                        HttpStatus.CREATED,
                        mapModelToDTO(userModel)
                    )
                )
            );
    }

    @Override
    @GetMapping
    public Mono<ResponseEntity<ResponseDTO<List<UserDTO>>>> findAll(
        UserCriteriaDTO userCriteriaDTO
    ) {
        return userService.findAll(mapCriteriaDTOToModel(userCriteriaDTO)).collectList()
            .map((List<UserModel> userModelList) -> ResponseEntity.ok()
                .body(
                    buildResponseDTO(
                        HttpStatus.OK,
                        userModelList.stream()
                            .map(UserMapper::mapModelToDTO)
                            .collect(Collectors.toList())
                    )
                )
            );
    }

    @Override
    @GetMapping(
        path = "/{id}"
    )
    public Mono<ResponseEntity<ResponseDTO<UserDTO>>> findById(
        String id
    ) {
        return userService.findById(id)
            .map((UserModel userModel) -> ResponseEntity.ok()
                .body(
                    buildResponseDTO(
                        HttpStatus.OK,
                        mapModelToDTO(userModel)
                    )
                )
            );
    }

    @Override
    @PutMapping(
        path = "/{id}",
        consumes = APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<ResponseDTO<UserDTO>>> update(
        String id,
        UpdateUserDTO updateUserDTO
    ) {
        updateUserDTO.setId(id);
        return userService.update(mapUpdateDTOToModel(updateUserDTO))
            .map((UserModel userModel) -> ResponseEntity.ok()
                .body(
                    buildResponseDTO(
                        HttpStatus.OK,
                        mapModelToDTO(userModel)
                    )
                )
            );
    }

    @Override
    @DeleteMapping(
        path = "/{id}"
    )
    public Mono<ResponseEntity<ResponseDTO<Object>>> delete(
        String id
    ) {
        return userService.delete(id)
            .thenReturn(
                ResponseEntity.ok()
                    .body(buildResponseDTO(HttpStatus.OK, id))
            );
    }
}
