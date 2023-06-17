package com.example.skeduler.controllers;

import com.example.skeduler.dto.TaskDto;
import com.example.skeduler.services.TaskService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

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
        taskService.create(new TaskDto("hello very important", "hello very important task",  (long) 1, "2024-01-01", "13:30", true, true));
        taskService.create(new TaskDto("hello important", "hello important task",  (long) 1, "2024-01-02", "20:30", true, false));
        taskService.create(new TaskDto("hello", "hello task",  (long) 1, "2024-01-03", "06:00", false, false));
        taskService.create(new TaskDto("hello very important", "hello very important task",  (long) 1, "2024-01-01", "10:30", true, true));
        taskService.create(new TaskDto("hello important", "hello important task",  (long) 1, "2024-01-02", "22:30", true, false));
        taskService.create(new TaskDto("hello", "hello task",  (long) 1, "2024-01-03", "10:30", false, false));
        taskService.create(new TaskDto("hello very important", "hello very important task",  (long) 1, "2024-01-01", "19:30", true, true));
        taskService.create(new TaskDto("hello important", "hello important task",  (long) 1, "2024-01-02", "18:30", true, false));
        taskService.create(new TaskDto("hello", "hello task",  (long) 1, "2024-01-03", "05:30", false, false));
    }

    @Test
    void createTask() {
        TaskDto taskDto = new TaskDto("bye", "bye task",  (long) 1, "2024-01-11", "18:30", false, false);
        taskController.createTask(taskDto);

        assertThat(taskController.allTasks((long) 1).size()).isEqualTo(10);
    }

    @Test
    void allTasks() {
        assertThat(taskController.allTasks((long) 1).size()).isEqualTo(10);
    }

    @Test
    void 알람_기능_테스트() {
        assertThat(taskController.morningAlarm((long) 1, LocalDate.parse("2024-01-01"))).isEqualTo(LocalTime.parse("09:30:00"));
        assertThat(taskController.morningAlarm((long) 1, LocalDate.parse("2024-01-02"))).isEqualTo(LocalTime.parse("12:00:00"));
        assertThat(taskController.morningAlarm((long) 1, LocalDate.parse("2024-01-03"))).isEqualTo(LocalTime.parse("04:30:00"));
    }
}