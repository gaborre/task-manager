package com.gabn.taskmanager.dto.user;

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
public class UpdateUserDTO extends CreateUserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6128340032370059944L;

    @JsonIgnore
    private String id;
}
