package com.kakaotechcampus.schedule_app.Lv1.controller;

import com.kakaotechcampus.schedule_app.Lv1.dto.CreateScheduleRequestDto;
import com.kakaotechcampus.schedule_app.Lv1.dto.ScheduleResponseDto;
import com.kakaotechcampus.schedule_app.Lv1.service.ScheduleService;
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
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto requestDto){
        ScheduleResponseDto savedSchedule = scheduleService.save(requestDto);

        return new ResponseEntity<>(savedSchedule, HttpStatus.CREATED);
    }

}
