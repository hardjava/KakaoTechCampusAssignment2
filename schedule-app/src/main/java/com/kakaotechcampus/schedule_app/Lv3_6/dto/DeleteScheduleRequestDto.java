package com.kakaotechcampus.schedule_app.Lv3_6.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteScheduleRequestDto {
    @NotBlank(message = "Password is Required")
    private final String password;
}
