package com.jsrdev.TasksAPI.domain.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddUserRequest(
        @NotBlank(message = "Is required")
        @Email(message = "Must be a valid email")
        String login,
        @NotBlank(message = "Is required")
        String password,
        //@NotNull(message = "Is required")
        @Valid UserRole role
) { }
