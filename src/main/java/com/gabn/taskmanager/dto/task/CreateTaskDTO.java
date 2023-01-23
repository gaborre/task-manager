package com.gabn.taskmanager.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8089847764081230982L;
    private String name;
    private String description;
}
