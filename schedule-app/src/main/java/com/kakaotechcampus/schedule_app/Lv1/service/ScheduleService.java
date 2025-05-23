package com.kakaotechcampus.schedule_app.Lv1.service;

import com.kakaotechcampus.schedule_app.Lv1.dto.CreateScheduleRequestDto;
import com.kakaotechcampus.schedule_app.Lv1.dto.ScheduleResponseDto;
import com.kakaotechcampus.schedule_app.Lv1.entity.Schedule;
import com.kakaotechcampus.schedule_app.Lv1.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(CreateScheduleRequestDto requestDto){
        Schedule schedule = new Schedule(requestDto.getUsername(), requestDto.getContents(), requestDto.getPassword());

        return scheduleRepository.saveSchedule(schedule);
    }
}
