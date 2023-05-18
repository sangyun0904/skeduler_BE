package com.example.skeduler.repositories;

import com.example.skeduler.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);

    List<Task> findByUserIdAndImportantTrue(Long userId);
    List<Task> findByUserIdAndVeryImportantTrue(Long userId);
    Optional<Task> findById(Long id);
    Optional<Task> findByUserIdAndStartDateTime(Long userId, LocalDateTime startDateTime);
}
