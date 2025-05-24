package com.kakaotechcampus.schedule_app.Lv3_6.controller;

import com.kakaotechcampus.schedule_app.Lv3_6.dto.AuthorResponseDto;
import com.kakaotechcampus.schedule_app.Lv3_6.dto.CreateAuthorRequestDto;
import com.kakaotechcampus.schedule_app.Lv3_6.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@Valid @RequestBody CreateAuthorRequestDto requestDto){
        AuthorResponseDto responseDto = authorService.createAuthor(requestDto.getEmail(), requestDto.getName());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
