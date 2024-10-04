package com.jsrdev.TasksAPI.domain.service;

import com.jsrdev.TasksAPI.domain.repository.TaskRepository;
import com.jsrdev.TasksAPI.domain.task.AddTaskRequest;
import com.jsrdev.TasksAPI.domain.task.Task;
import com.jsrdev.TasksAPI.domain.task.UpdateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Task save(AddTaskRequest addTask) {
        var taskRequest = new Task(addTask);
        return taskRepository.save(taskRequest);
    }

    public Task getReferenceById(Long id) {
        return taskRepository.getReferenceById(id);
    }

    public void updateTask(Task task, UpdateTask updateTask) {
        task.updateData(updateTask);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}
