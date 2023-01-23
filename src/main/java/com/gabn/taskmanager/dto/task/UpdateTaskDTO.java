package com.gabn.taskmanager.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UpdateTaskDTO extends CreateTaskDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2725437583158796876L;

    @JsonIgnore
    private String id;
}
