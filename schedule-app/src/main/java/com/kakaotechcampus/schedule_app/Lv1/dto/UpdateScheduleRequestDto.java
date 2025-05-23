package com.kakaotechcampus.schedule_app.Lv1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateScheduleRequestDto {
    private final String password;
    private final String username;
    private final String contents;
}
