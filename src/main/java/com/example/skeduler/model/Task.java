package com.example.skeduler.model;

import com.example.skeduler.model.enums.Importance;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @ManyToOne
    private Member member;
    @NotBlank
    @Column(nullable = false)
    private String title;
    @Lob
    @NotBlank
    private String content = "";
    @NotNull
    @Column(nullable = false)
    private LocalDateTime startDateTime;
    @NotNull
    private LocalDateTime endDateTime;
    @Builder.Default
    private final LocalDateTime uploadDateTime = LocalDateTime.now();
    @Builder.Default
    private Importance importance = Importance.NORMAL;


}
