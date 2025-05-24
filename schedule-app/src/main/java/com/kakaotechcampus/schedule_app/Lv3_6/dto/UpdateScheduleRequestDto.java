package com.kakaotechcampus.schedule_app.Lv3_6.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateScheduleRequestDto {
    @NotBlank(message = "Contents is Required")
    @Size(max = 200, message = "Contents are limited to 200 characters")
    private final String contents;

    @NotBlank(message = "Password is Required")
    private final String password;
}
