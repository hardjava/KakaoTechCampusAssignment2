package com.kakaotechcampus.schedule_app.Lv1.repository;

import com.kakaotechcampus.schedule_app.Lv1.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository {
    Schedule saveSchedule(Schedule schedule);
    List<Schedule> findAll(String username, LocalDate modifiedAt);
    Schedule findByIdOrElseThrow(Long id);
    Schedule update(Schedule schedule);
}
