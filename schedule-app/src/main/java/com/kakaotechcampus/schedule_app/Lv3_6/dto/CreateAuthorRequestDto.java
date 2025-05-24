package com.kakaotechcampus.schedule_app.Lv3_6.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateAuthorRequestDto {

    @NotBlank(message = "Email Is Required")
    @Email(message = "Proper Email Format Is Required")
    private final String email;

    @NotBlank(message = "Name Is Required")
    private final String name;
}
