package com.example.skeduler.controllers;

import com.example.skeduler.model.Task;
import com.example.skeduler.services.JwtService;
import com.example.skeduler.services.MemberService;
import com.example.skeduler.services.TaskService;
import com.example.skeduler.dto.TaskDto;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;
    private final JwtService jwtService;
    private final MemberService memberService;

    TaskController(TaskService taskService, JwtService jwtService, MemberService memberService) {
        this.taskService = taskService;
        this.jwtService = jwtService;
        this.memberService = memberService;
    }

    @GetMapping("/")
    public List<Task> allTasks(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        final String jwt = authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        long userId = memberService.loadUserByUsername(username).getId();

        return taskService.getAllTasks(userId);
    }

    @PostMapping("/")
    public long createTask(@RequestBody TaskDto taskDto) {
        return taskService.create(taskDto);
    }

    @GetMapping("/{day}/")
    public LocalTime morningAlarm(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @PathVariable(value = "day")  LocalDate day) {

        final String jwt = authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        long userId = memberService.loadUserByUsername(username).getId();

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

    @GetMapping("/medium/")
    public List<Task> importantTasks(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        final String jwt = authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        long userId = memberService.loadUserByUsername(username).getId();

        return taskService.getImportantTasks(userId);
    }

    @GetMapping("/high/")
    public List<Task> veryImportantTasks(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        final String jwt = authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        long userId = memberService.loadUserByUsername(username).getId();

        return taskService.getVeryImportantTasks(userId);
    }

}
