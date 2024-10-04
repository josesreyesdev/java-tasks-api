package com.jsrdev.TasksAPI.controller;

import com.jsrdev.TasksAPI.domain.service.TaskService;
import com.jsrdev.TasksAPI.domain.task.AddTaskRequest;
import com.jsrdev.TasksAPI.domain.task.Task;
import com.jsrdev.TasksAPI.domain.task.TaskResponse;
import com.jsrdev.TasksAPI.domain.task.UpdateTask;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<TaskResponse> getTasks(@PageableDefault(size = 30, sort = {"title"}) Pageable pageable) {
        Page<Task> taskPage = taskService.findAll(pageable);
        return taskPage.stream()
                .map(TaskResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public TaskResponse getTask(@PathVariable Long id) {

        Optional<Task> taskOptional = taskService.isTaskInBD(id);

        if (taskOptional.isPresent()) {
            //Task task = taskService.getReferenceById(taskOptional.get().getId());
            return new TaskResponse(taskOptional.get());
        } else {
            throw new RuntimeException("Task not found, with this id: " + id);
        }
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateTask updateTask) {
        Task task = taskService.getReferenceById(updateTask.id());
        taskService.updateTask(task, updateTask);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        Task task = taskService.getReferenceById(id);
        taskService.deleteTask(task);
    }
}
