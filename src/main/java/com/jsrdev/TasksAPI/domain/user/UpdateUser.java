package com.jsrdev.TasksAPI.domain.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdateUser(
        @NotNull(message = "Is required")
        Long id,
        String password,
        @Valid UserRole role,
        Boolean active
) { }
