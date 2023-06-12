package com.example.skeduler.controllers;

import com.example.skeduler.dto.TaskDto;
import com.example.skeduler.services.TaskService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskControllerTest {

    @Autowired
    TaskController taskController;

    @Autowired
    TaskService taskService;

    @BeforeAll
    void beforeAll() {
        taskService.create(new TaskDto("hello very important", "hello very important task",  (long) 1, "2023-01-01", "10:30", true, true));
        taskService.create(new TaskDto("hello important", "hello important task",  (long) 1, "2023-01-02", "10:30", true, false));
        taskService.create(new TaskDto("hello", "hello task",  (long) 1, "2023-01-03", "10:30", false, false));
    }

    @Test
    void createTask() {
        TaskDto taskDto = new TaskDto("bye", "bye task",  (long) 1, "2023-01-11", "12:30", false, false);
        taskController.createTask(taskDto);

        assertThat(taskController.allTasks((long) 1).size()).isEqualTo(4);
    }

    @Test
    void allTasks() {
        assertThat(taskController.allTasks((long) 1).size()).isEqualTo(4);
    }
}