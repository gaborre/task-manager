package com.gabn.taskmanager.dto.user;

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
public class CreateUserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 7189333538608424886L;
    private String identification;
    private String name;
    private String lastName;
    private String address;
    private String phone;
}
