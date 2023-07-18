package com.example.skeduler.controllers;

import com.example.skeduler.model.Member;
import com.example.skeduler.model.Task;
import com.example.skeduler.services.JwtService;
import com.example.skeduler.services.MemberService;
import com.example.skeduler.services.TaskService;
import com.example.skeduler.dto.TaskCreateDto;
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

        Member member = memberService.loadUserByUsername(username);

        return taskService.getAllTasks(member);
    }

    @PostMapping("/")
    public long createTask(@RequestBody TaskCreateDto taskDto) {
        return taskService.create(taskDto);
    }

    @GetMapping("/{day}")
    public List<Task> allDayTask(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @RequestParam("day") String day) {
        LocalDate date = LocalDate.parse(day);

        final String jwt = authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        Member member = memberService.loadUserByUsername(username);

        return taskService.getAllDayTasks(member, date);
    }
}
