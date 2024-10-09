package com.jsrdev.TasksAPI.domain.models.task;

import java.time.LocalDateTime;

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
