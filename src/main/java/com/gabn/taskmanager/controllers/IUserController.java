package com.gabn.taskmanager.controllers;

import com.gabn.taskmanager.dto.ResponseDTO;
import com.gabn.taskmanager.dto.user.CreateUserDTO;
import com.gabn.taskmanager.dto.user.UpdateUserDTO;
import com.gabn.taskmanager.dto.user.UserCriteriaDTO;
import com.gabn.taskmanager.dto.user.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "User services")
public interface IUserController {

    @Operation(summary = "Creates an user")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "User created",
                content = {
                    @Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseDTO.class)
                    )
                }
            )
        }
    )
    Mono<ResponseEntity<ResponseDTO<UserDTO>>> create(
        @RequestBody CreateUserDTO createUserDTO
    );

    @Operation(summary = "Gets all users")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "User list",
                content = {
                    @Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseDTO.class)
                    )
                }
            )
        }
    )
    Mono<ResponseEntity<ResponseDTO<List<UserDTO>>>> findAll(
        @Valid UserCriteriaDTO userCriteriaDTO
    );

    @Operation(summary = "Gets an user by id")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "User by id",
                content = {
                    @Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseDTO.class)
                    )
                }
            )
        }
    )
    Mono<ResponseEntity<ResponseDTO<UserDTO>>> findById(
        @PathVariable(value = "id") String id
    );

    @Operation(summary = "Update an user by id")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "User updated",
                content = {
                    @Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseDTO.class)
                    )
                }
            )
        }
    )
    Mono<ResponseEntity<ResponseDTO<UserDTO>>> update(
        @PathVariable(value = "id") String id,
        @RequestBody UpdateUserDTO updateUserDTO
    );

    @Operation(summary = "Delete an user by id")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Deleted response",
                content = {
                    @Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseDTO.class)
                    )
                }
            )
        }
    )
    Mono<ResponseEntity<ResponseDTO>> delete(
        @PathVariable(value = "id") String id
    );
}
