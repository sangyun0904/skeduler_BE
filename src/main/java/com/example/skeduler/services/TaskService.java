package com.example.skeduler.services;

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
        long userId = taskDto.userId();
        LocalDateTime dateTime = LocalDateTime.parse(taskDto.startDate() + "T" + taskDto.startTime() + ":00");

        validateTask(userId, dateTime);

        Task task = Task.builder()
                .title(taskDto.title())
                .userId(taskDto.userId())
                .content(taskDto.content())
                .startDateTime(LocalDateTime.parse(taskDto.startDate() + "T" + taskDto.startTime() + ":00"))
                .important(taskDto.important())
                .veryImportant(taskDto.veryImportant())
                .build();
        taskRepository.save(task);
        return task.getId();

    }

    public Optional<Task> getTask(Long id) { return taskRepository.findById(id); }

    public List<Task> getAllTasks(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public List<Task> getImportantTasks(Long userId) {
        return taskRepository.findByUserIdAndImportantTrue(userId);
    }

    public List<Task> getVeryImportantTasks(Long userId) {
        return taskRepository.findByUserIdAndVeryImportantTrue(userId);
    }

    private void validateTask(Long userId, LocalDateTime startTime) {
        taskRepository.findByUserIdAndStartDateTime(userId, startTime)
                .ifPresent(e -> {
                    throw new IllegalStateException("그 시간은 이미 채워져 있습니다.");
                });
    }

    public long updateTask(long taskId, TaskDto taskDto) {
        Task task = Task.builder()
                .id(taskId)
                .title(taskDto.title())
                .userId(taskDto.userId())
                .content(taskDto.content())
                .startDateTime(LocalDateTime.parse(taskDto.startDate() + "T" + taskDto.startTime() + ":00"))
                .important(taskDto.important())
                .veryImportant(taskDto.veryImportant())
                .build();

        taskRepository.save(task);
        return task.getId();

    }

    public List<Task> getDayTask(Long userId, LocalDate day) {
        return taskRepository.findByUserIdAndDate(userId, day);
    }
}
