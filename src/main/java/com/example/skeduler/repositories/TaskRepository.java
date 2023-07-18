package com.example.skeduler.repositories;

import com.example.skeduler.model.Member;
import com.example.skeduler.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByMember(Member member);

    Optional<Task> findById(Long id);

    @Query(value = "SELECT * FROM task WHERE member_id = ?1 AND start_date <= ?2 AND end_date >= ?2", nativeQuery = true)
    List<Task> findAllByMemberAndDate(Long member, LocalDate date);
}
