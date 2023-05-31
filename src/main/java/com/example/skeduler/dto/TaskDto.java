package com.example.skeduler.dto;

public record TaskDto(String title, String content, Long userId, String startDate, String startTime, boolean important, boolean veryImportant) {}
