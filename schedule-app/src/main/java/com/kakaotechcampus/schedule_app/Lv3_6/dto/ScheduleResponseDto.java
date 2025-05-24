package com.kakaotechcampus.schedule_app.Lv3_6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private final Long id;
    private final Long authorId;
    private final String contents;

    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
}
