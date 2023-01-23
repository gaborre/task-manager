package com.gabn.taskmanager.mocks;

import com.gabn.taskmanager.collections.Task;
import com.gabn.taskmanager.dto.task.CreateTaskDTO;
import com.gabn.taskmanager.dto.task.TaskDTO;
import com.gabn.taskmanager.dto.task.UpdateTaskDTO;
import com.gabn.taskmanager.models.TaskModel;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public final class TaskMock {

    public static Task getTask() {
        return Task.builder()
            .id("1")
            .name("Fulano")
            .description("Description")
            .createdDate(LocalDateTime.now(ZoneOffset.UTC))
            .updatedDate(LocalDateTime.now(ZoneOffset.UTC))
            .build();
    }

    public static TaskDTO getTaskDTO() {
        return TaskDTO.builder()
            .id("1")
            .name("Fulano")
            .description("Description")
            .build();
    }

    public static TaskModel getTaskModel() {
        return TaskModel.builder()
            .id("1")
            .name("Fulano")
            .description("Description")
            .build();
    }

    public static TaskModel getTaskModelWithDate() {
        return TaskModel.builder()
            .id("1")
            .name("Fulano")
            .description("Description")
            .createdDate(LocalDateTime.now(ZoneOffset.UTC))
            .updatedDate(LocalDateTime.now(ZoneOffset.UTC))
            .build();
    }

    public static List<Task> getTaskList() {
        return List.of(
            getTask()
        );
    }

    public static List<TaskModel> getTaskModelList() {
        return List.of(
            getTaskModel()
        );
    }

    public static TaskModel getTaskModelForCreate() {
        return TaskModel.builder()
            .name("Fulano")
            .description("Description")
            .createdDate(LocalDateTime.now(ZoneOffset.UTC))
            .updatedDate(LocalDateTime.now(ZoneOffset.UTC))
            .build();
    }

    public static CreateTaskDTO getCreateTaskDTO() {
        return CreateTaskDTO.builder()
            .name("Fulano")
            .description("Description")
            .build();
    }

    public static UpdateTaskDTO getUpdateTaskDTO() {
        return UpdateTaskDTO.builder()
            .id("1")
            .name("Fulano")
            .description("Description")
            .build();
    }

    public static TaskModel getTaskModelForUpdate() {
        return TaskModel.builder()
            .id("1")
            .name("Fulano")
            .description("Description")
            .createdDate(LocalDateTime.now(ZoneOffset.UTC))
            .updatedDate(LocalDateTime.now(ZoneOffset.UTC))
            .build();
    }
}
