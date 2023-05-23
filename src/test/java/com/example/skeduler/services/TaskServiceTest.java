package com.example.skeduler.services;

import com.example.skeduler.dto.TaskDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TaskServiceTest {

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

        assertThat(taskService.getTask(id).get().getTitle()).isEqualTo("hello");

    }

    @Test
    void 테스크_생성_모든입력() {

        TaskDto taskDto = TaskDto
                .builder()
                .title("hello")
                .userId((long) 1)
                .content("hello task")
                .startDate("2023-01-02")
                .startTime("11:30")
                .important(true)
                .veryImportant(true)
                .build();

        long id = taskService.create(taskDto);

        assertThat(taskService.getTask(id).get().getContent()).isEqualTo("hello task");
        assertThat(taskService.getTask(id).get().isImportant()).isEqualTo(true);
        assertThat(taskService.getTask(id).get().isVeryImportant()).isEqualTo(true);

    }

    @Test
    void 테스크_필수입력_에러() {

        try {
            TaskDto taskDto = TaskDto.builder()
                    .userId((long) 1)
                    .content("hello")
                    .build();
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("title is marked non-null but is null");
        }

    }


    @Test
    void 같은시간_테스크_중복() {

        TaskDto taskDto1 = TaskDto
                .builder()
                .title("bye1")
                .userId((long) 1)
                .startDate("2023-01-03")
                .startTime("10:30")
                .build();

        taskService.create(taskDto1);

        TaskDto taskDto2 = TaskDto
                .builder()
                .title("bye2")
                .userId((long) 1)
                .startDate("2023-01-03")
                .startTime("10:30")
                .build();

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> taskService.create(taskDto2));
        assertThat(e.getMessage()).isEqualTo("그 시간은 이미 채워져 있습니다.");


    }

    @Test
    void 테스크_수정() {
        TaskDto taskDto1 = TaskDto
                .builder()
                .title("bye1")
                .userId((long) 1)
                .startDate("2023-01-04")
                .startTime("10:30")
                .build();

        long taskId1 = taskService.create(taskDto1);

        TaskDto taskDto2 = TaskDto
                .builder()
                .title("bye2")
                .userId((long) 1)
                .startDate("2023-01-04")
                .startTime("11:30")
                .build();

        long taskId2 = taskService.updateTask(taskId1, taskDto2);

        assertThat(taskService.getTask(taskId1).get().getTitle()).isEqualTo("bye2");
    }
}