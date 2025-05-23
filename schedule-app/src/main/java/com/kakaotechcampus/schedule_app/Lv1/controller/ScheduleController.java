package com.kakaotechcampus.schedule_app.Lv1.controller;

import com.kakaotechcampus.schedule_app.Lv1.dto.CreateScheduleRequestDto;
import com.kakaotechcampus.schedule_app.Lv1.dto.ScheduleResponseDto;
import com.kakaotechcampus.schedule_app.Lv1.dto.ScheduleWithDateResponseDto;
import com.kakaotechcampus.schedule_app.Lv1.service.ScheduleService;
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
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto requestDto){
        ScheduleResponseDto scheduleResponseDto = scheduleService
                .save(
                        requestDto.getUsername(),
                        requestDto.getContents(),
                        requestDto.getPassword());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleWithDateResponseDto>> findAll(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate modifiedAt
            ){
        List<ScheduleWithDateResponseDto> scheduleWithDateResponseDtoList = scheduleService.findAll(username, modifiedAt);

        return new ResponseEntity<>(scheduleWithDateResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleWithDateResponseDto> findById(@PathVariable Long id){
        ScheduleWithDateResponseDto scheduleWithDateResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleWithDateResponseDto, HttpStatus.OK);
    }
}
