package com.example.skeduler.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class TaskDto {

    @NonNull
    private String title;
    @Builder.Default
    private String content = "";
    @NonNull
    private Long userId;
    @NonNull
    private String startDate;
    @NonNull
    private String startTime;
    @Builder.Default
    private boolean important = false;
    @Builder.Default
    private boolean veryImportant = false;
}
