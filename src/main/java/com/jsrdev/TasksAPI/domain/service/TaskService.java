package com.jsrdev.TasksAPI.domain.service;

import com.jsrdev.TasksAPI.domain.repository.TaskRepository;
import com.jsrdev.TasksAPI.domain.task.AddTaskRequest;
import com.jsrdev.TasksAPI.domain.task.Task;
import com.jsrdev.TasksAPI.domain.task.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskResponse> findAll() {
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream()
                .map(TaskResponse::new)
                .toList();
    }

    public TaskResponse save(AddTaskRequest addTask) {
        System.out.println("Add Task: " + addTask);
        var taskRequest = new Task(addTask);
        Task responseBD = taskRepository.save(taskRequest);
        return new TaskResponse(responseBD);
    }
}
