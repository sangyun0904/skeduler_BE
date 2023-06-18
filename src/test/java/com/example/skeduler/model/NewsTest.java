package com.example.skeduler.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NewsTest {

    @Test
    void getterTest() {

        LocalDateTime dateTime = LocalDateTime.now();

        News news = News
                .builder()
                .id((long) 1)
                .title("news")
                .author("김상윤")
                .description("설명")
                .content("content")
                .publishedDateTime(dateTime)
                .url("www.google.com")
                .urlToImage("www.google.com/image")
                .build();

        assertThat(news.getId()).isEqualTo((long) 1);
        assertThat(news.getTitle()).isEqualTo("news");
        assertThat(news.getAuthor()).isEqualTo("김상윤");
        assertThat(news.getDescription()).isEqualTo("설명");
        assertThat(news.getContent()).isEqualTo("content");
        assertThat(news.getPublishedDateTime()).isEqualTo(dateTime);
        assertThat(news.getUrl()).isEqualTo("www.google.com");
        assertThat(news.getUrlToImage()).isEqualTo("www.google.com/image");


    }

    @Test
    void toStringTest() {
        LocalDateTime dateTime = LocalDateTime.now();

        News news = News
                .builder()
                .id((long) 2)
                .title("news")
                .author("김상윤")
                .description("설명")
                .content("content")
                .publishedDateTime(dateTime)
                .url("www.google.com")
                .urlToImage("www.google.com/image")
                .build();

        String str_news = "News{" +
                "id=" + 2 +
                ", title='" + "news" + '\'' +
                ", author='" + "김상윤" + '\'' +
                ", description='" + "설명" + '\'' +
                ", url='" + "www.google.com" + '\'' +
                ", urlToImage='" + "www.google.com/image" + '\'' +
                ", publishedDateTime=" + dateTime +
                ", content='" + "content" + '\'' +
                '}';

        assertThat(news.toString()).isEqualTo(str_news);
    }

}