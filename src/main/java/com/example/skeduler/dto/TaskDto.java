package com.example.skeduler.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class TaskDto {

    private String title;
    private String content;
    @NonNull
    private Long userId;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private boolean important;
    private boolean veryImportant;
}
