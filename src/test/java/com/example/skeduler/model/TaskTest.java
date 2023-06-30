package com.example.skeduler.model;

import com.example.skeduler.dto.TaskDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

class TaskTest {

    private Member member = Member
            .builder()
            .id((long) 1)
            .username("sang")
            .password("pass")
            .email("sang@gmail.com")
            .githubId("sang")
            .build();

    private Category category = new Category((long) 1, "Finance", Collections.emptyList(), Collections.emptyList());

    @Test
    void fromDto() {
        TaskDto taskDto = new TaskDto("hello very important", "hello very important task", member, "2022-01-01", "10:30", true, true, category);
        Task task = Task.builder()
                .title(taskDto.title())
                .member(taskDto.member())
                .category(taskDto.category())
                .content(taskDto.content())
                .startDateTime(LocalDateTime.parse(taskDto.startDate() + "T" + taskDto.startTime() + ":00"))
                .important(taskDto.important())
                .veryImportant(taskDto.veryImportant())
                .build();
        System.out.println(task);

    }

    @Test
    void getStartDateTime() {
        LocalDateTime timenow = LocalDateTime.now();

        Task task = new Task((long) 1, member, category, "hello", "", timenow, timenow, true, true);

        assertThat(task.getStartDateTime()).isEqualTo(timenow);
    }

    @Test
    void getUploadDateTime() {
        LocalDateTime timenow = LocalDateTime.now();

        Task task = Task.builder()
                .id((long) 1)
                .member(member)
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
                .member(member)
                .title("hello")
                .uploadDateTime(LocalDateTime.now())
                .startDateTime(LocalDateTime.now())
                .build();

        assertThat(task.getMember().getId()).isEqualTo((long) 1);
    }

    @Test
    void builder() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime upload = LocalDateTime.now();

        Task task = Task.builder()
                .id((long) 1)
                .member(member)
                .title("hello")
                .uploadDateTime(upload)
                .startDateTime(start)
                .build();

        assertThat(Task.builder()
                .id((long) 1)
                .member(member)
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
                .member(member)
                .title(null)
                .uploadDateTime(upload)
                .startDateTime(start)
                .build());

        assertThrows(NullPointerException.class, () -> Task.builder()
                .id((long) 1)
                .member(null)
                .title("hello")
                .uploadDateTime(upload)
                .startDateTime(start)
                .build());

        assertThrows(NullPointerException.class, () -> Task.builder()
                .id((long) 1)
                .member(member)
                .title("hello")
                .uploadDateTime(upload)
                .startDateTime(null)
                .build());
    }

}