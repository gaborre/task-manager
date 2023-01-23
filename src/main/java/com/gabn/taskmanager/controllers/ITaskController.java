package com.gabn.taskmanager.controllers;

import com.gabn.taskmanager.dto.ResponseDTO;
import com.gabn.taskmanager.dto.task.CreateTaskDTO;
import com.gabn.taskmanager.dto.task.TaskCriteriaDTO;
import com.gabn.taskmanager.dto.task.TaskDTO;
import com.gabn.taskmanager.dto.task.UpdateTaskDTO;
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

@Tag(name = "Task services")
public interface ITaskController {

    @Operation(summary = "Creates an task")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "Task created",
                content = {
                    @Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseDTO.class)
                    )
                }
            )
        }
    )
    Mono<ResponseEntity<ResponseDTO<TaskDTO>>> create(
        @RequestBody CreateTaskDTO createTaskDTO
    );

    @Operation(summary = "Gets all tasks")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Task list",
                content = {
                    @Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseDTO.class)
                    )
                }
            )
        }
    )
    Mono<ResponseEntity<ResponseDTO<List<TaskDTO>>>> findAll(
        @Valid TaskCriteriaDTO taskCriteriaDTO
    );

    @Operation(summary = "Gets an task by id")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Task by id",
                content = {
                    @Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseDTO.class)
                    )
                }
            )
        }
    )
    Mono<ResponseEntity<ResponseDTO<TaskDTO>>> findById(
        @PathVariable(value = "id") String id
    );

    @Operation(summary = "Update an task by id")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Task updated",
                content = {
                    @Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseDTO.class)
                    )
                }
            )
        }
    )
    Mono<ResponseEntity<ResponseDTO<TaskDTO>>> update(
        @PathVariable(value = "id") String id,
        @RequestBody UpdateTaskDTO updateTaskDTO
    );

    @Operation(summary = "Delete an task by id")
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
    Mono<ResponseEntity<ResponseDTO<Object>>> delete(
        @PathVariable(value = "id") String id
    );
}
