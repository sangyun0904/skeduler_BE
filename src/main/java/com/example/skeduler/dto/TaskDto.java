package com.example.skeduler.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private String content;
    private Long userId;
    private String startDateTime;
    private String endDateTime;
    private boolean important;
    private boolean veryImportant;
}
