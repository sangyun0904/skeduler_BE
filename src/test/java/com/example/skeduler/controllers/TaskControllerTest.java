package com.example.skeduler.controllers;

import com.example.skeduler.dto.TaskDto;
import com.example.skeduler.model.Category;
import com.example.skeduler.model.Member;
import com.example.skeduler.services.TaskService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskControllerTest {

    private Member member = Member
            .builder()
            .id((long) 1)
            .username("sang")
            .password("pass")
            .email("sang@gmail.com")
            .githubId("sang")
            .build();

    private Category category = new Category((long) 1, "Finance", Collections.emptyList(), Collections.emptyList());

    @Autowired
    TaskController taskController;

    @Autowired
    TaskService taskService;

    @BeforeAll
    void beforeAll() {
        taskService.create(new TaskDto("hello very important", "hello very important task",  member, "2024-01-01", "13:30", true, true, category));
        taskService.create(new TaskDto("hello important", "hello important task",  member, "2024-01-02", "20:30", true, false, category));
        taskService.create(new TaskDto("hello", "hello task",  member, "2024-01-03", "06:00", false, false, category));
        taskService.create(new TaskDto("hello very important", "hello very important task",  member, "2024-01-01", "10:30", true, true, category));
        taskService.create(new TaskDto("hello important", "hello important task",  member, "2024-01-02", "22:30", true, false, category));
        taskService.create(new TaskDto("hello", "hello task",  member, "2024-01-03", "10:30", false, false, category));
        taskService.create(new TaskDto("hello very important", "hello very important task",  member, "2024-01-01", "19:30", true, true, category));
        taskService.create(new TaskDto("hello important", "hello important task",  member, "2024-01-02", "18:30", true, false, category));
        taskService.create(new TaskDto("hello", "hello task",  member, "2024-01-03", "05:30", false, false, category));
    }
    
}