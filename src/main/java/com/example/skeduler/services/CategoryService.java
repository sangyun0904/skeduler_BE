package com.example.skeduler.services;

import com.example.skeduler.model.Category;
import com.example.skeduler.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public long create(Category category) {
        return categoryRepository.save(category).getId();
    }
}
