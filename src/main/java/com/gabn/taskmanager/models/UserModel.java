package com.gabn.taskmanager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 5457470511345794214L;
    private String id;
    private String identification;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
