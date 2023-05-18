package com.example.skeduler.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private String content;
    private Long userId;
    private String startDate;
    private String startTime;
    private boolean important;
    private boolean veryImportant;
}
