package com.example.skeduler.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryTest {

    @Test
    void test() {
        Category category = Category
                .builder()
                .name("new category")
                .build();

        assertThat(category.getName()).isEqualTo("new category");
        System.out.println(category.getId());
    }

}