package com.jsrdev.TasksAPI.domain.models.task;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateTask(
        @NotNull Long id,
        String title,
        String description,
        @Future(message = "The expirationDate must be in the future")
        @Valid LocalDateTime expirationDate,
        @Valid TaskStatus taskStatus
) { }
