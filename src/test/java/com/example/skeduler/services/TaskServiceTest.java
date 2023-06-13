package com.example.skeduler.services;

import com.example.skeduler.dto.TaskDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @BeforeAll
    public void afterEach() {
        taskService.create(new TaskDto("hello very important", "hello very important task",  (long) 1, "2022-01-01", "10:30", true, true));
        taskService.create(new TaskDto("hello important", "hello important task",  (long) 1, "2022-01-01", "11:30", true, false));
        taskService.create(new TaskDto("hello", "hello task",  (long) 1, "2022-01-01", "11:00", false, false));
        taskService.create(new TaskDto("hello very important", "hello very important task",  (long) 1, "2022-01-01", "12:30", true, true));
        taskService.create(new TaskDto("hello important", "hello important task",  (long) 1, "2022-01-01", "13:30", true, false));
        taskService.create(new TaskDto("hello", "hello task",  (long) 1, "2022-01-01", "14:30", false, false));
        taskService.create(new TaskDto("hello very important", "hello very important task",  (long) 1, "2022-01-01", "15:30", true, true));
        taskService.create(new TaskDto("hello important", "hello important task",  (long) 1, "2022-01-01", "16:30", true, false));
        taskService.create(new TaskDto("hello", "hello task",  (long) 1, "2022-01-01", "17:30", false, false));
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

    @Test
    void 알람기능_테스트() {
        LocalDate date = LocalDate.parse("2022-01-01");
        System.out.println(taskService.getDayTask((long) 1, date));
    }
}