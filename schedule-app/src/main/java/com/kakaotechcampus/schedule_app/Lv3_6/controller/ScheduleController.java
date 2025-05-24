package com.kakaotechcampus.schedule_app.Lv3_6.controller;

import com.kakaotechcampus.schedule_app.Lv3_6.dto.CreateScheduleRequestDto;
import com.kakaotechcampus.schedule_app.Lv3_6.dto.ScheduleWithAuthorIdResponseDto;
import com.kakaotechcampus.schedule_app.Lv3_6.dto.ScheduleWithAuthorResponseDto;
import com.kakaotechcampus.schedule_app.Lv3_6.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ScheduleWithAuthorResponseDto>> findAllSchedules(
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate modifiedAt){

        List<ScheduleWithAuthorResponseDto> list = scheduleService.findAll(authorId, modifiedAt);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
