package com.gabn.taskmanager.dto.user;

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
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1556940352063493399L;
    private String id;
    private String identification;
    private String name;
    private String lastName;
    private String address;
    private String phone;

    @JsonInclude(Include.NON_NULL)
    private String createdDate;

    @JsonInclude(Include.NON_NULL)
    private String updatedDate;
}
