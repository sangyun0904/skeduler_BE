package com.example.skeduler.controllers;

import com.example.skeduler.model.Task;
import com.example.skeduler.repositories.TaskRepository;
import com.example.skeduler.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task/{userId}")
    public List<Task> allTasks(@PathVariable(value = "userId") Long userId) {
        return taskService.getAllTasks(userId);
    }

    @PostMapping("/task")
    public long createTask(@RequestBody Task task) {
        return taskService.create(task);
    }
}
