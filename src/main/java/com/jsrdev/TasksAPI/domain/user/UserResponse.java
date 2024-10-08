package com.jsrdev.TasksAPI.domain.user;

public record UserResponse(
        Long id,
        String login,
        UserRole role,
        Boolean active
) {
    public UserResponse(User user) {
        this(user.getId(), user.getLogin(), user.getRole(), user.getActive());
    }
}
