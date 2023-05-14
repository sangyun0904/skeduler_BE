package com.example.skeduler.services;

import com.example.skeduler.model.Task;
import com.example.skeduler.repositories.TaskRepository;
import com.example.skeduler.dto.TaskDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {this.taskRepository = taskRepository;}

    public long create(TaskDto taskDto) {
        Task task = new Task();
        task.setContent(taskDto.getContent());
        task.setId(taskDto.getUserId());
        task.setStartDateTime(LocalDateTime.parse(taskDto.getStartDateTime() + "T00:00:00"));
        task.setEndDateTime(LocalDateTime.parse(taskDto.getEndDateTime() + "T11:59:59"));
        task.setUploadDateTime(LocalDateTime.now());
        taskRepository.save(task);
        return task.getId();
    }

    public List<Task> getAllTasks(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public Long craete(Task task) {
        taskRepository.save(task);
        return task.getId();
    }

}
