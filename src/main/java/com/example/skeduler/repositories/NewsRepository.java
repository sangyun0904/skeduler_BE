package com.example.skeduler.repositories;

import com.example.skeduler.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
