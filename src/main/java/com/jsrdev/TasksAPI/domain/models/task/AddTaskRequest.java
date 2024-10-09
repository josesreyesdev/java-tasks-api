package com.jsrdev.TasksAPI.domain.models.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AddTaskRequest(
        @JsonProperty("title")
        @NotBlank(message = "title is required")
        String title,

        @JsonProperty("description")
        String description,

        //@JsonProperty("expiration_date")
        @NotNull(message = "expirationDate is required")
        @Future(message = "The expirationDate must be in the future")
        LocalDateTime expirationDate,

        //@JsonProperty("task_status")
        @NotNull(message = "taskStatus is required")
        @Valid TaskStatus taskStatus
) { }
