package com.example.skeduler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String title;
    @Builder.Default
    private String content = "";

    @NonNull
    private Long userId;
    @NonNull
    private LocalDateTime startDateTime;
    @Builder.Default
    private final LocalDateTime uploadDateTime = LocalDateTime.now();
    @Builder.Default
    private boolean important = false;
    @Builder.Default
    private boolean veryImportant = false;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", startDateTime=" + startDateTime +
                ", uploadDateTime=" + uploadDateTime +
                ", important=" + important +
                ", veryImportant=" + veryImportant +
                '}';
    }
}
