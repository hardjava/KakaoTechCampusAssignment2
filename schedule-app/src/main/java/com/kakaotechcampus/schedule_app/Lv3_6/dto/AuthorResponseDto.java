package com.kakaotechcampus.schedule_app.Lv3_6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthorResponseDto {
    private final Long id;
    private final String email;
    private final String name;
}
