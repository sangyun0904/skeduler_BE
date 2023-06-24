package com.example.skeduler.controllers;

import com.example.skeduler.model.Task;
import com.example.skeduler.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final TaskService taskService;

    @GetMapping("/{userId}")
    public List<Task> tasks(@PathVariable Long userId) {
       return taskService.getAllTasks(userId);
    }


}
