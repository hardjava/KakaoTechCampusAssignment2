package com.kakaotechcampus.schedule_app.Lv3_6.controller;

import com.kakaotechcampus.schedule_app.Lv3_6.dto.CreateScheduleRequestDto;
import com.kakaotechcampus.schedule_app.Lv3_6.dto.ScheduleWithAuthorIdResponseDto;
import com.kakaotechcampus.schedule_app.Lv3_6.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleWithAuthorIdResponseDto> createSchedule(@Valid @RequestBody CreateScheduleRequestDto requestDto){
        ScheduleWithAuthorIdResponseDto responseDto = scheduleService.createSchedule(
                requestDto.getAuthorId(),
                requestDto.getContents(),
                requestDto.getPassword()
        );

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
