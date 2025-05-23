package com.kakaotechcampus.schedule_app.Lv1.service;

import com.kakaotechcampus.schedule_app.Lv1.dto.ScheduleResponseDto;
import com.kakaotechcampus.schedule_app.Lv1.dto.ScheduleWithDateResponseDto;
import com.kakaotechcampus.schedule_app.Lv1.entity.Schedule;
import com.kakaotechcampus.schedule_app.Lv1.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String username, String contents, String password){
        Schedule schedule = new Schedule(username, contents, password);
        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getUsername(), savedSchedule.getContents());
    }

    public List<ScheduleWithDateResponseDto> findAll(String username, LocalDate modifiedAt){
        return scheduleRepository.findAll(username, modifiedAt)
                .stream()
                .map(ScheduleWithDateResponseDto::toDto)
                .toList();
    }

    public ScheduleWithDateResponseDto findById(Long id){
        Schedule savedSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleWithDateResponseDto(
                savedSchedule.getId(),
                savedSchedule.getUsername(),
                savedSchedule.getContents(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }
}
