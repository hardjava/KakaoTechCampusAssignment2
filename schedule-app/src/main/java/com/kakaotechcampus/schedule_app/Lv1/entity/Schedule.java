package com.kakaotechcampus.schedule_app.Lv1.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Schedule {
    private Long id;
    private String username;
    private String contents;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Schedule(String username, String contents, String password) {
        this.username = username;
        this.contents = contents;
        this.password = password;
    }
}
