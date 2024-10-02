package com.jsrdev.TasksAPI.domain.task;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tasks")
@Entity(name = "task")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id
    private UUID id;
    private String title;
    private String description;

    @JsonProperty("expiration_date")
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @JsonProperty("task_status")
    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus taskStatus;

    public Task(AddTaskRequest addTask) {
        this.id = UUID.randomUUID();
        this.title = addTask.title();
        if (addTask.description() == null)
            this.description = "No description";
        else
            this.description = addTask.description();
        this.expirationDate = addTask.expirationDate();
        this.taskStatus = addTask.taskStatus();
    }
}
