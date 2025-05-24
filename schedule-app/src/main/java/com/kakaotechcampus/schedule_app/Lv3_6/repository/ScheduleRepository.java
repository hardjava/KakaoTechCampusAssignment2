package com.kakaotechcampus.schedule_app.Lv3_6.repository;

import com.kakaotechcampus.schedule_app.Lv3_6.entity.Schedule;
import com.kakaotechcampus.schedule_app.Lv3_6.entity.ScheduleWithAuthor;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository {
    Schedule createSchedule(Schedule schedule);
    List<ScheduleWithAuthor> findAll(Long authorId, LocalDate modifiedAt);
    Schedule findScheduleByIdOrElseThrow(Long id);
    Schedule updateSchedule(Schedule schedule);
    void deleteSchedule(Long id);
}
