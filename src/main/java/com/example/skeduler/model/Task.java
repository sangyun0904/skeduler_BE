package com.example.skeduler.model;

import com.example.skeduler.model.enums.Importance;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member member;
    @NotBlank
    private String title;
    @Lob
    @NotBlank
    private String content = "";
    @NotNull
    private LocalDate startDate;
    private LocalTime startTime;
    @NotNull
    private LocalDate endDate;
    private LocalTime endTime;
    @Builder.Default
    private final LocalDateTime uploadDateTime = LocalDateTime.now();
    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private Importance importance = Importance.NORMAL;


}
