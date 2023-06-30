package com.example.skeduler.dto;

import com.example.skeduler.model.Category;
import com.example.skeduler.model.Member;

public record TaskDto(String title, String content, Member member, String startDate, String startTime, boolean important, boolean veryImportant, Category category) {}
