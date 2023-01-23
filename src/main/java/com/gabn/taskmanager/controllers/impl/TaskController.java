package com.gabn.taskmanager.controllers.impl;

import com.gabn.taskmanager.controllers.ITaskController;
import com.gabn.taskmanager.dto.ResponseDTO;
import com.gabn.taskmanager.dto.task.CreateTaskDTO;
import com.gabn.taskmanager.dto.task.TaskCriteriaDTO;
import com.gabn.taskmanager.dto.task.TaskDTO;
import com.gabn.taskmanager.dto.task.UpdateTaskDTO;
import com.gabn.taskmanager.mappers.TaskMapper;
import com.gabn.taskmanager.models.TaskModel;
import com.gabn.taskmanager.services.ITaskService;
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

import static com.gabn.taskmanager.mappers.TaskMapper.mapCreateDTOToModel;
import static com.gabn.taskmanager.mappers.TaskMapper.mapCriteriaDTOToModel;
import static com.gabn.taskmanager.mappers.TaskMapper.mapModelToDTO;
import static com.gabn.taskmanager.mappers.TaskMapper.mapUpdateDTOToModel;
import static com.gabn.taskmanager.utils.ResponseUtils.buildResponseDTO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
    value = "/api/v1/tasks",
    produces = APPLICATION_JSON_VALUE
)
public class TaskController implements ITaskController {
    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    @PostMapping(
        consumes = APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<ResponseDTO<TaskDTO>>> create(CreateTaskDTO createTaskDTO) {
        return taskService.create(mapCreateDTOToModel(createTaskDTO))
            .map((TaskModel taskModel) -> ResponseEntity.status(HttpStatus.CREATED)
                .body(
                    buildResponseDTO(
                        HttpStatus.CREATED,
                        mapModelToDTO(taskModel)
                    )
                )
            );
    }

    @Override
    @GetMapping
    public Mono<ResponseEntity<ResponseDTO<List<TaskDTO>>>> findAll(
        TaskCriteriaDTO taskCriteriaDTO
    ) {
        return taskService.findAll(mapCriteriaDTOToModel(taskCriteriaDTO)).collectList()
            .map((List<TaskModel> taskModelList) -> ResponseEntity.ok()
                .body(
                    buildResponseDTO(
                        HttpStatus.OK,
                        taskModelList.stream()
                            .map(TaskMapper::mapModelToDTO)
                            .collect(Collectors.toList())
                    )
                )
            );
    }

    @Override
    @GetMapping(
        path = "/{id}"
    )
    public Mono<ResponseEntity<ResponseDTO<TaskDTO>>> findById(
        String id
    ) {
        return taskService.findById(id)
            .map((TaskModel taskModel) -> ResponseEntity.ok()
                .body(
                    buildResponseDTO(
                        HttpStatus.OK,
                        mapModelToDTO(taskModel)
                    )
                )
            );
    }

    @Override
    @PutMapping(
        path = "/{id}",
        consumes = APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<ResponseDTO<TaskDTO>>> update(
        String id,
        UpdateTaskDTO updateTaskDTO
    ) {
        updateTaskDTO.setId(id);
        return taskService.update(mapUpdateDTOToModel(updateTaskDTO))
            .map((TaskModel taskModel) -> ResponseEntity.ok()
                .body(
                    buildResponseDTO(
                        HttpStatus.OK,
                        mapModelToDTO(taskModel)
                    )
                )
            );
    }

    @Override
    @DeleteMapping(
        path = "/{id}"
    )
    public Mono<ResponseEntity<ResponseDTO>> delete(
        String id
    ) {
        return taskService.delete(id)
            .map(response -> ResponseEntity.ok()
                .body(buildResponseDTO(HttpStatus.OK, null))
            );
    }
}
