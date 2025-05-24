package com.kakaotechcampus.schedule_app.Lv3_6.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateScheduleRequestDto {

    @NotNull(message = "Author ID is Required")
    private final Long authorId;

    @NotBlank(message = "Contents is Required")
    @Size(max = 200, message = "Contents are limited to 200 characters")
    private final String contents;

    @NotBlank(message = "Password is Required")
    private final String password;
}
