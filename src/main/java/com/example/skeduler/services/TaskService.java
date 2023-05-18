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
        task.setStartDateTime(LocalDateTime.parse(taskDto.getStartDateTime() + "T00:00:00"));
        task.setEndDateTime(LocalDateTime.parse(taskDto.getEndDateTime() + "T11:59:59"));
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


}
