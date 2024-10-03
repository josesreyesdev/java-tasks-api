package com.jsrdev.TasksAPI.domain.repository;

import com.jsrdev.TasksAPI.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
