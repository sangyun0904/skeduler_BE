package com.example.skeduler.repositories;

import com.example.skeduler.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);

    List<Task> findByUserIdAndImportantTrue(Long userId);
    List<Task> findByUserIdAndVeryImportantTrue(Long userId);
}
