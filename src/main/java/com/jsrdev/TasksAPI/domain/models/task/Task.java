package com.jsrdev.TasksAPI.domain.models.task;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "tasks")
@Entity(name = "task")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @JsonProperty("expirationDate")
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @JsonProperty("taskStatus")
    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus taskStatus;

    public Task(AddTaskRequest addTask) {
        this.title = addTask.title();
        this.description = (addTask.description() == null)
                ? "No description"
                : addTask.description();
        this.expirationDate = addTask.expirationDate();
        this.taskStatus = addTask.taskStatus();
    }

    public void updateData(UpdateTask updateTask) {
        if (updateTask.title() != null) {
            this.title = updateTask.title();
        }
        if (updateTask.description() != null) {
            this.description = updateTask.description();
        }
        if (updateTask.expirationDate() != null) {
            this.expirationDate = updateTask.expirationDate();
        }
        if (updateTask.taskStatus() != null) {
            this.taskStatus = updateTask.taskStatus();
        }
    }
}
