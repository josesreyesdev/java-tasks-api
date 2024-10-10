package com.jsrdev.TasksAPI.domain.models.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignIn(
        @NotBlank(message = "Is required")
        @Email(message = "Must be a valid email")
        String login,
        @NotBlank(message = "Is required")
        String pass
){}
