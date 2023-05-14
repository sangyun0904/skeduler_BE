package com.example.skeduler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long userId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private LocalDateTime uploadDateTime;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", uploadDateTime=" + uploadDateTime +
                '}';
    }
}
