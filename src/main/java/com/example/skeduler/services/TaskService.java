package com.example.skeduler.services;

import com.example.skeduler.model.Member;
import com.example.skeduler.model.Task;
import com.example.skeduler.dto.TaskDto;
import com.example.skeduler.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;


    public long create(TaskDto taskDto) {
        Member member = taskDto.member();
        LocalDateTime dateTime = LocalDateTime.parse(taskDto.startDate() + "T" + taskDto.startTime() + ":00");

        validateTask(member, dateTime);

        Task task = Task.builder()
                .title(taskDto.title())
                .member(taskDto.member())
                .category(taskDto.category())
                .content(taskDto.content())
                .startDateTime(LocalDateTime.parse(taskDto.startDate() + "T" + taskDto.startTime() + ":00"))
                .important(taskDto.important())
                .veryImportant(taskDto.veryImportant())
                .build();
        taskRepository.save(task);
        return task.getId();

    }

    public Optional<Task> getTask(long id) { return taskRepository.findById(id); }

    public List<Task> getAllTasks(Member member) {
        return taskRepository.findByMember(member);
    }

    public List<Task> getImportantTasks(Member member) {
        return taskRepository.findByMember(member);
    }

    public List<Task> getVeryImportantTasks(Member member) {
        return taskRepository.findByMemberAndVeryImportantTrue(member);
    }

    private void validateTask(Member member, LocalDateTime startTime) {
        taskRepository.findByMemberAndStartDateTime(member, startTime)
                .ifPresent(e -> {
                    throw new IllegalStateException("그 시간은 이미 채워져 있습니다.");
                });
    }

    public long updateTask(long taskId, TaskDto taskDto) {
        Task task = Task.builder()
                .id(taskId)
                .title(taskDto.title())
                .member(taskDto.member())
                .content(taskDto.content())
                .startDateTime(LocalDateTime.parse(taskDto.startDate() + "T" + taskDto.startTime() + ":00"))
                .important(taskDto.important())
                .veryImportant(taskDto.veryImportant())
                .build();

        taskRepository.save(task);
        return task.getId();

    }

    public List<Task> getDayTask(Member member, LocalDate date) {
        return taskRepository.findByMemberAndDate(member.getId(), date.toString());
    }
}
