package com.example.skeduler.services;

import com.example.skeduler.dto.TaskDto;
import com.example.skeduler.model.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private TaskService taskService;

    @AfterEach
    public void afterEach() {

    }


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

    @Test
    void 테스크_필수입력_에러() {

        try {
            TaskDto taskDto = TaskDto.builder()
                    .userId((long) 1)
                    .content("hello")
                    .build();
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("title is marked non-null but is null");
        }

    }


    @Test
    void 같은시간_테스크_중복() {
    }
}