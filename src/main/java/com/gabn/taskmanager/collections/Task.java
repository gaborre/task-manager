package com.gabn.taskmanager.collections;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String name;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String description;

    @Indexed(direction = IndexDirection.ASCENDING)
    private LocalDateTime createdDate;

    @Indexed(direction = IndexDirection.ASCENDING)
    private LocalDateTime updatedDate;
}
