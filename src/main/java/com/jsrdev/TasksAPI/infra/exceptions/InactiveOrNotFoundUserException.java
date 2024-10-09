package com.jsrdev.TasksAPI.infra.exceptions;

public class InactiveOrNotFoundUserException extends RuntimeException {
    public InactiveOrNotFoundUserException(String ex) {
        super(ex);
    }
}
