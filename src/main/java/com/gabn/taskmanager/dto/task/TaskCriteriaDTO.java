package com.gabn.taskmanager.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCriteriaDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5945739180448898078L;
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String initDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String endDate;
}
