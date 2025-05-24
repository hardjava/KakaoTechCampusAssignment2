package com.kakaotechcampus.schedule_app.Lv3_6.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleWithAuthor {
    private Long authorId;
    private String name;
    private String email;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
