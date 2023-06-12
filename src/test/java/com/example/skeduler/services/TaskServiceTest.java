package com.example.skeduler.services;

import com.example.skeduler.dto.TaskDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @AfterEach
    public void afterEach() {

    }


    @Test
    void 테스크_생성() {

        TaskDto taskDto = new TaskDto("hello", "hello task",  (long) 2, "2023-01-01", "10:30", true, true);
        long id = taskService.create(taskDto);

        assertThat(taskService.getTask(id).get().getContent()).isEqualTo("hello task");
        assertThat(taskService.getTask(id).get().isImportant()).isEqualTo(true);
        assertThat(taskService.getTask(id).get().isVeryImportant()).isEqualTo(true);

    }


    @Test
    void 같은시간_테스크_중복() {

        TaskDto taskDto1 = new TaskDto("bye1", "",  (long) 3, "2023-01-03", "10:30", false, false);
        taskService.create(taskDto1);

        TaskDto taskDto2 = new TaskDto("bye2", "",  (long) 3, "2023-01-03", "10:30", false, false);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> taskService.create(taskDto2));
        assertThat(e.getMessage()).isEqualTo("그 시간은 이미 채워져 있습니다.");


    }

    @Test
    void 테스크_수정() {

        TaskDto taskDto1 = new TaskDto("bye1", "content",  (long) 4, "2023-01-04", "10:30", false, false);

        long taskId1 = taskService.create(taskDto1);

        TaskDto taskDto2 = new TaskDto("bye2", "",  (long) 4, "2023-01-04", "10:30", false, false);

        taskService.updateTask(taskId1, taskDto2);

        assertThat(taskService.getTask(taskId1).get().getTitle()).isEqualTo("bye2");
    }
}