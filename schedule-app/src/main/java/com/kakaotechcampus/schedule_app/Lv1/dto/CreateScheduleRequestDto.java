package com.kakaotechcampus.schedule_app.Lv1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateScheduleRequestDto {
    private final String username;
    private final String contents;
    private final String password;
}
