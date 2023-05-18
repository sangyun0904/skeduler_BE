package com.example.skeduler.services;

import com.example.skeduler.dto.TaskDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;


    @Test
    void 테스크_생성() {

        TaskDto taskDto = new TaskDto();
        taskDto.setContent("hello");
        taskDto.setImportant(true);
        taskDto.setVeryImportant(false);
        taskDto.setEndDateTime("2023-09-04");
        taskDto.setStartDateTime("2023-05-18");
        taskDto.setUserId((long) 1);

        long id = taskService.create(taskDto);

        Assertions.assertThat(taskService.getTask(id).get().getContent()).isEqualTo("hello");


    }



}