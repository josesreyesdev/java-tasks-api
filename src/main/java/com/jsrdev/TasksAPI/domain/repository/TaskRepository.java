package com.jsrdev.TasksAPI.domain.repository;

import com.jsrdev.TasksAPI.domain.models.task.Task;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @NonNull
    Page<Task> findAll(@NonNull Pageable pageable);

}
