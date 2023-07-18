package com.example.skeduler.services;

import com.example.skeduler.model.Member;
import com.example.skeduler.model.Task;
import com.example.skeduler.dto.TaskCreateDto;
import com.example.skeduler.model.enums.Importance;
import com.example.skeduler.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;


    public long create(TaskCreateDto taskDto) {
        Member member = taskDto.member();
        LocalDateTime dateTime = LocalDateTime.parse(taskDto.startDate() + "T" + taskDto.startTime() + ":00");

        Task task = Task.builder()
                .title(taskDto.title())
                .member(taskDto.member())
                .content(taskDto.content())
                .startDate(LocalDate.parse(taskDto.startDate()))
                .startTime(LocalTime.parse(taskDto.startTime()))
                .endDate(LocalDate.parse(taskDto.endDate()))
                .endTime(LocalTime.parse(taskDto.endTime()))
                .uploadDateTime(LocalDateTime.now())
                .importance(Importance.valueOf(taskDto.importance()))
                .build();
        taskRepository.save(task);
        return task.getId();

    }

    public Optional<Task> getTask(long id) { return taskRepository.findById(id); }

    public List<Task> getAllTasks(Member member) {
        return taskRepository.findByMember(member);
    }

    public long updateTask(long taskId, TaskCreateDto taskDto) {
        Task task = Task.builder()
                .id(taskId)
                .title(taskDto.title())
                .member(taskDto.member())
                .content(taskDto.content())
                .startDate(LocalDate.parse(taskDto.startDate()))
                .startTime(LocalTime.parse(taskDto.startTime()))
                .endDate(LocalDate.parse(taskDto.endDate()))
                .endTime(LocalTime.parse(taskDto.endTime()))
                .uploadDateTime(LocalDateTime.now())
                .importance(Importance.valueOf(taskDto.importance()))
                .build();

        taskRepository.save(task);
        return task.getId();

    }

}
