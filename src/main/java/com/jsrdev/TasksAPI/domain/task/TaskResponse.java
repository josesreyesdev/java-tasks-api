package com.jsrdev.TasksAPI.domain.task;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(
        Long id,
        String title,
        String description,
        LocalDateTime expirationDate,
        String taskStatus
) {
    public TaskResponse(Task task) {
        this(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getExpirationDate(),
                task.getTaskStatus().name()
        );
    }
}
