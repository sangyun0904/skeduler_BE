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

    @Query(value = "SELECT * FROM task WHERE user_id = ?1 AND CAST(start_date_time AS DATE) = ?2 ORDER BY start_date_time", nativeQuery = true)
//    @Query(value = "SELECT * FROM Task WHERE user_id = ?1", nativeQuery = true)
    List<Task> findByUserIdAndDate(Long userId, String date);
}
