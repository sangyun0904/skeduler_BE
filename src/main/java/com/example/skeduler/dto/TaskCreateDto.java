package com.example.skeduler.dto;

import com.example.skeduler.model.Category;
import com.example.skeduler.model.Member;
import com.example.skeduler.model.enums.Importance;

public record TaskCreateDto(
        String title,
        String content,
        Member member,
        String startDate,
        String startTime,
        String endDate,
        String endTime,
        String importance
) {
}
