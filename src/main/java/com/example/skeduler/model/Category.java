package com.example.skeduler.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks = new ArrayList<Task>();

    @OneToMany(mappedBy = "category")
    private List<News> news = new ArrayList<News>();

}
