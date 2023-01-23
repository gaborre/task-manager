package com.gabn.taskmanager.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1890572710333689789L;
    private String id;
    private String name;
    private String description;

    @JsonInclude(Include.NON_NULL)
    private String createdDate;

    @JsonInclude(Include.NON_NULL)
    private String updatedDate;
}
