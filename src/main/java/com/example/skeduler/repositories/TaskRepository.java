package com.example.skeduler.repositories;

import com.example.skeduler.model.Member;
import com.example.skeduler.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByMember(Member member);

    Optional<Task> findById(Long id);

}
