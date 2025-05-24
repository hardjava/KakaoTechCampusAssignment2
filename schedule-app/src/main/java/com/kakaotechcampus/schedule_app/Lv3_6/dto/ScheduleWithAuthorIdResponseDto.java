package com.kakaotechcampus.schedule_app.Lv3_6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScheduleWithAuthorIdResponseDto {
    private Long id;
    private Long authorId;
    private String contents;
}
