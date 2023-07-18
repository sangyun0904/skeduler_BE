package com.example.skeduler.services;

import com.example.skeduler.dto.TaskCreateDto;
import com.example.skeduler.model.Member;
import com.example.skeduler.repositories.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeAll
    static void beforeAll(@Autowired TaskService taskService, @Autowired MemberRepository memberRepository) {
        Member member = Member.builder().username("admin").password("password").build();
        memberRepository.save(member);
        TaskCreateDto dto = new TaskCreateDto(
                "title",
                "content",
                member,
                "2023-03-04",
                "00:00:00",
                "2023-03-06",
                "11:59:59",
                "IMPORTANT"
                );
        taskService.create(dto);
    }

    @Test
    void getAllDayTasks() {
        Member member = memberRepository.findByUsername("admin").orElseThrow();
        Assertions.assertThat(taskService.getAllDayTasks(member, LocalDate.parse("2023-03-05")).size()).isEqualTo(1);
        Assertions.assertThat(taskService.getAllDayTasks(member, LocalDate.parse("2023-03-04")).size()).isEqualTo(1);
        Assertions.assertThat(taskService.getAllDayTasks(member, LocalDate.parse("2023-03-06")).size()).isEqualTo(1);
        Assertions.assertThat(taskService.getAllDayTasks(member, LocalDate.parse("2023-03-03")).size()).isEqualTo(0);
        Assertions.assertThat(taskService.getAllDayTasks(member, LocalDate.parse("2023-03-07")).size()).isEqualTo(0);
    }
}