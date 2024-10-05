package com.jsrdev.TasksAPI.domain.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddUserRequest(
        @NotBlank(message = "Is required")
        String login,
        @NotBlank(message = "Is required")
        String password,
        @NotNull(message = "Is required")
        @Valid UserRole role
) { }
