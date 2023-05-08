package com.example.skeduler.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {

    private String content;
    private Long userId;
    private String startDateTime;
    private String endDateTime;
}
