package com.gabn.taskmanager.mappers;

import com.gabn.taskmanager.collections.Task;
import com.gabn.taskmanager.dto.task.CreateTaskDTO;
import com.gabn.taskmanager.dto.task.TaskCriteriaDTO;
import com.gabn.taskmanager.dto.task.TaskDTO;
import com.gabn.taskmanager.dto.task.UpdateTaskDTO;
import com.gabn.taskmanager.models.TaskModel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.gabn.taskmanager.utils.DateTimeUtils.getLocalDateTimeForCriteria;
import static com.gabn.taskmanager.utils.DateTimeUtils.getStringDateFromLocalDateTime;

@NoArgsConstructor
public final class TaskMapper {

    public static TaskModel mapCriteriaDTOToModel(TaskCriteriaDTO taskCriteriaDTO) {
        return TaskModel.builder()
            .id(taskCriteriaDTO.getId())
            .name(taskCriteriaDTO.getName())
            .description(taskCriteriaDTO.getDescription())
            .createdDate(getLocalDateTimeForCriteria(taskCriteriaDTO.getInitDate()))
            .updatedDate(getLocalDateTimeForCriteria(taskCriteriaDTO.getEndDate()))
            .build();
    }

    public static TaskModel mapCreateDTOToModel(CreateTaskDTO createTaskDTO) {
        return TaskModel.builder()
            .name(createTaskDTO.getName())
            .description(createTaskDTO.getDescription())
            .build();
    }

    public static TaskModel mapUpdateDTOToModel(UpdateTaskDTO updateTaskDTO) {
        return TaskModel.builder()
            .id(updateTaskDTO.getId())
            .name(updateTaskDTO.getName())
            .description(updateTaskDTO.getDescription())
            .build();
    }

    public static Task mapModelToCollectionForCreation(TaskModel taskModel) {
        return Task.builder()
            .name(taskModel.getName())
            .description(taskModel.getDescription())
            .createdDate(LocalDateTime.now(ZoneOffset.UTC))
            .build();
    }

    public static TaskModel mapCollectionToModel(Task task) {
        return TaskModel.builder()
            .id(task.getId())
            .name(task.getName())
            .description(task.getDescription())
            .createdDate(task.getCreatedDate())
            .updatedDate(task.getUpdatedDate())
            .build();
    }

    public static TaskDTO mapModelToDTO(TaskModel taskModel) {
        return TaskDTO.builder()
            .id(taskModel.getId())
            .name(taskModel.getName())
            .description(taskModel.getDescription())
            .createdDate(getStringDateFromLocalDateTime(taskModel.getCreatedDate()))
            .updatedDate(getStringDateFromLocalDateTime(taskModel.getUpdatedDate()))
            .build();
    }
}
