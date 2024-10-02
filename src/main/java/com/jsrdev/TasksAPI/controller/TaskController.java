package com.jsrdev.TasksAPI.controller;

import com.jsrdev.TasksAPI.domain.service.TaskService;
import com.jsrdev.TasksAPI.domain.task.AddTaskRequest;
import com.jsrdev.TasksAPI.domain.task.TaskResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public TaskResponse addTask(@RequestBody @Valid AddTaskRequest addTask) {
        return taskService.save(addTask);
    }

    @GetMapping
    public List<TaskResponse> getTasks() {
        return taskService.findAll();
    }
}
