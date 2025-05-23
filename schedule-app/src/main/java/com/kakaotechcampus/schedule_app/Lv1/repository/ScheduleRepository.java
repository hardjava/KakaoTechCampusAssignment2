package com.kakaotechcampus.schedule_app.Lv1.repository;

import com.kakaotechcampus.schedule_app.Lv1.dto.ScheduleResponseDto;
import com.kakaotechcampus.schedule_app.Lv1.entity.Schedule;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);
}
