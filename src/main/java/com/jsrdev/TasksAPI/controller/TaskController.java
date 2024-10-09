package com.jsrdev.TasksAPI.controller;

import com.jsrdev.TasksAPI.domain.service.TaskService;
import com.jsrdev.TasksAPI.domain.models.task.AddTaskRequest;
import com.jsrdev.TasksAPI.domain.models.task.Task;
import com.jsrdev.TasksAPI.domain.models.task.TaskResponse;
import com.jsrdev.TasksAPI.domain.models.task.UpdateTask;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @Transactional
    public ResponseEntity<TaskResponse> addTask(
            @RequestBody @Valid AddTaskRequest addTask,
            UriComponentsBuilder uriBuilder
    ) {
        Task task = taskService.save(addTask);
        URI url = uriBuilder.path("/api/tasks/{id}")
                .buildAndExpand(task.getId())
                .toUri();
        return ResponseEntity.created(url).body(new TaskResponse(task)); // status 201-created
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks(
            @PageableDefault(size = 30, sort = {"title"}) Pageable pageable
    ) {
        Page<Task> taskPage = taskService.findAll(pageable);
        List<TaskResponse> tasks = taskPage.stream()
                .map(TaskResponse::new)
                .toList();
        return ResponseEntity.ok(tasks); //status 200-ok
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        var task = taskService.getReferenceById(id);
        return ResponseEntity.ok(new TaskResponse(task)); // status 200-ok
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TaskResponse> update(@RequestBody @Valid UpdateTask updateTask) {
        Task task = taskService.getReferenceById(updateTask.id());
        taskService.updateTask(task, updateTask);
        return ResponseEntity.ok(new TaskResponse(task)); //status 200-ok
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<TaskResponse> delete(@PathVariable Long id) {
        Task task = taskService.getReferenceById(id);
        taskService.deleteTask(task);
        return ResponseEntity.noContent().build(); //status 204-No Content
    }
}
