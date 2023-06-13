package com.example.skeduler.repositories;

import com.example.skeduler.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);

    List<Task> findByUserIdAndImportantTrue(Long userId);
    List<Task> findByUserIdAndVeryImportantTrue(Long userId);
    Optional<Task> findById(Long id);
    Optional<Task> findByUserIdAndStartDateTime(Long userId, LocalDateTime startDateTime);

    @Query(value = "SELECT * FROM Task WHERE user_id = ?1 and DATE(start_date_time) = ?2", nativeQuery = true)
    List<Task> findByUserIdAndDate(Long userId, LocalDate date);
}
