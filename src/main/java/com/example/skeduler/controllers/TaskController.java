package com.example.skeduler.controllers;

import com.example.skeduler.model.Task;
import com.example.skeduler.services.TaskService;
import com.example.skeduler.dto.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{userId}")
    public List<Task> allTasks(@PathVariable(value = "userId") Long userId) {
        return taskService.getAllTasks(userId);
    }

    @PostMapping("/")
    public long createTask(@RequestBody TaskDto taskDto) {
        return taskService.create(taskDto);
    }

    @GetMapping("/{userId}/{day}")
    public LocalTime morningAlarm(@PathVariable(value = "userId") Long userId, @PathVariable(value = "day")  LocalDate day) {
        List<Task> taskList = taskService.getDayTask(userId, day);
        LocalTime firstTaskTime = taskList.get(0).getStartDateTime().toLocalTime();
        if (taskList.size() != 0) {
            if (firstTaskTime.compareTo(LocalTime.NOON) < 0) {
                return taskList.get(0).getStartDateTime().toLocalTime().minusHours(1);
            }
        }
//        return taskList.get(0).getStartDateTime();
        return LocalTime.NOON;
    }

}
