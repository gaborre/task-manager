package com.gabn.taskmanager.collections;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    private String identification;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String name;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String lastName;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String address;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String phone;

    @Indexed(direction = IndexDirection.ASCENDING)
    private LocalDateTime createdDate;

    @Indexed(direction = IndexDirection.ASCENDING)
    private LocalDateTime updatedDate;
}
