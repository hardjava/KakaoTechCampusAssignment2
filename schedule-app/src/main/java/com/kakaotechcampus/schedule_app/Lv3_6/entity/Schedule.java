package com.kakaotechcampus.schedule_app.Lv3_6.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Schedule {
    private Long id;
    private Long authorId;
    private String contents;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Schedule(Long authorId, String contents, String password) {
        this.authorId = authorId;
        this.contents = contents;
        this.password = password;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
