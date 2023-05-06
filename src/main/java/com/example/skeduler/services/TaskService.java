package com.example.skeduler.services;

import com.example.skeduler.model.Task;
import com.example.skeduler.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {this.taskRepository = taskRepository;}

    public long create(Task task) {
        taskRepository.save(task);
        return task.getId();
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Long craete(Task task) {
        taskRepository.save(task);
        return task.getId();
    }

}
