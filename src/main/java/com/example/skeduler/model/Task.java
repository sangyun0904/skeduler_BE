package com.example.skeduler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String title;
    private String content;

    @NonNull
    private Long userId;
    @NonNull
    private LocalDateTime startDateTime;
    private final LocalDateTime uploadDateTime = LocalDateTime.now();
    private boolean important;
    private boolean veryImportant;

}
