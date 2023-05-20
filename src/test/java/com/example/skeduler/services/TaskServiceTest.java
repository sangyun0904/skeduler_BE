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

        TaskDto taskDto = TaskDto
                .builder()
                .title("hello")
                .userId((long) 1)
                .startDate("2023-01-01")
                .startTime("10:30")
                .build();

        long id = taskService.create(taskDto);

        Assertions.assertThat(taskService.getTask(id).get().getTitle()).isEqualTo("hello");

    }

    @Test
    void 테스크_생성_모든입력() {

        TaskDto taskDto = TaskDto
                .builder()
                .title("hello")
                .userId((long) 1)
                .content("hello task")
                .startDate("2023-01-01")
                .startTime("10:30")
                .important(true)
                .veryImportant(true)
                .build();

        long id = taskService.create(taskDto);

        Assertions.assertThat(taskService.getTask(id).get().getContent()).isEqualTo("hello task");
        Assertions.assertThat(taskService.getTask(id).get().isImportant()).isEqualTo(true);
        Assertions.assertThat(taskService.getTask(id).get().isVeryImportant()).isEqualTo(true);

    }


}