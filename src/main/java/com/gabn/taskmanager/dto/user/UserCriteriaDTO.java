package com.gabn.taskmanager.dto.user;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCriteriaDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5183296188701174975L;
    private String id;

    @NotEmpty
    private String identification;

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String address;

    @NotEmpty
    private String phone;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String initDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String endDate;
}
