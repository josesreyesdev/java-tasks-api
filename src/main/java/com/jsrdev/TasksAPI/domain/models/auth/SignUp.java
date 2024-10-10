package com.jsrdev.TasksAPI.domain.models.auth;

import com.jsrdev.TasksAPI.domain.models.user.UserRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUp(
        @NotBlank(message = "Is required")
        @Email(message = "Must be a valid email")
        String login,
        @NotBlank(message = "Is required")
        String password,
        @Valid UserRole role
) {
}
