package com.example.skeduler.services;

import com.example.skeduler.model.Task;
import com.example.skeduler.dto.TaskDto;
import com.example.skeduler.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;


    public long create(TaskDto taskDto) {
        Task task = new Task();
        task.setContent(taskDto.getContent());
        task.setId(taskDto.getUserId());
        task.setStartDateTime(LocalDateTime.parse(taskDto.getStartDate() + "T" + taskDto.getStartTime() + ":00"));
        task.setUploadDateTime(LocalDateTime.now());
        task.setImportant(taskDto.isImportant());
        task.setVeryImportant(taskDto.isVeryImportant());
        taskRepository.save(task);
        return task.getId();
    }

    public Optional<Task> getTask(Long id) { return taskRepository.findById(id); }

    public List<Task> getAllTasks(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    private void validateTask(Long userId, LocalDateTime startTime) {
        taskRepository.findByUserIdAndStartDateTime(userId, startTime)
                .ifPresent(e -> {
                    throw new IllegalStateException("그 시간은 이미 채워져 있습니다.");
                });
    }

}
