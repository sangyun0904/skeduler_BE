package com.example.skeduler.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

class TaskTest {

    @Test
    void getStartDateTime() {
        LocalDateTime timenow = LocalDateTime.now();

        Task task = new Task((long) 1, "hello", "", (long) 1, timenow, timenow, true, true);

        assertThat(task.getStartDateTime()).isEqualTo(timenow);
    }

    @Test
    void getUploadDateTime() {
        LocalDateTime timenow = LocalDateTime.now();

        Task task = Task.builder()
                .id((long) 1)
                .userId((long) 1)
                .title("hello")
                .uploadDateTime(timenow)
                .startDateTime(LocalDateTime.now())
                .build();

        assertThat(task.getUploadDateTime()).isEqualTo(timenow);
    }

    @Test
    void getUserId() {

        Task task = Task.builder()
                .id((long) 1)
                .userId((long) 1)
                .title("hello")
                .uploadDateTime(LocalDateTime.now())
                .startDateTime(LocalDateTime.now())
                .build();

        assertThat(task.getUserId()).isEqualTo((long) 1);
    }

    @Test
    void builder() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime upload = LocalDateTime.now();

        Task task = Task.builder()
                .id((long) 1)
                .userId((long) 1)
                .title("hello")
                .uploadDateTime(upload)
                .startDateTime(start)
                .build();

        assertThat(Task.builder()
                .id((long) 1)
                .userId((long) 1)
                .title("hello")
                .uploadDateTime(upload)
                .startDateTime(start)
                .toString()).isEqualTo("Task.TaskBuilder(id=1, title=hello, content$value=null, userId=1, startDateTime=" + start + ", uploadDateTime$value=" + upload + ", important$value=false, veryImportant$value=false)");

        assertThat(task.getId()).isEqualTo((long) 1);
        String result = "Task{" +
                "id=" + 1 +
                ", title='" + "hello" + '\'' +
                ", content='" + '\'' +
                ", userId=" + 1 +
                ", startDateTime=" + start +
                ", uploadDateTime=" + upload +
                ", important=" + "false" +
                ", veryImportant=" + "false" +
                '}';
        assertThat(task.toString()).isEqualTo(result);
    }

    @Test
    void buildException() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime upload = LocalDateTime.now();

        assertThrows(NullPointerException.class, () -> Task.builder()
                .id((long) 1)
                .userId((long) 1)
                .title(null)
                .uploadDateTime(upload)
                .startDateTime(start)
                .build());

        assertThrows(NullPointerException.class, () -> Task.builder()
                .id((long) 1)
                .userId(null)
                .title("hello")
                .uploadDateTime(upload)
                .startDateTime(start)
                .build());

        assertThrows(NullPointerException.class, () -> Task.builder()
                .id((long) 1)
                .userId((long) 1)
                .title("hello")
                .uploadDateTime(upload)
                .startDateTime(null)
                .build());
    }

    @Test
    void constructorException() {
        assertThrows(NullPointerException.class, () -> new Task((long) 1, null, "content", (long) 1, LocalDateTime.now(), LocalDateTime.now(), true, true));
        assertThrows(NullPointerException.class, () -> new Task((long) 1, "title", "content", null, LocalDateTime.now(), LocalDateTime.now(), true, true));
        assertThrows(NullPointerException.class, () -> new Task((long) 1, "title", "content", (long) 1, null, LocalDateTime.now(), true, true));
    }
}