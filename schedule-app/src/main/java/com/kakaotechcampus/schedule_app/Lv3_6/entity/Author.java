package com.kakaotechcampus.schedule_app.Lv3_6.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Author {
    private Long id;
    private String email;
    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Author(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
