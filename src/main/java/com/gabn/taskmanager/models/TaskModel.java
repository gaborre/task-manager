package com.gabn.taskmanager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class TaskModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 3447451875136203517L;
    private String id;
    private String name;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
