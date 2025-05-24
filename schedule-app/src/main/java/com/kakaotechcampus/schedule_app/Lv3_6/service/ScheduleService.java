package com.kakaotechcampus.schedule_app.Lv3_6.service;

import com.kakaotechcampus.schedule_app.Lv3_6.dto.ScheduleWithAuthorIdResponseDto;
import com.kakaotechcampus.schedule_app.Lv3_6.entity.Author;
import com.kakaotechcampus.schedule_app.Lv3_6.entity.Schedule;
import com.kakaotechcampus.schedule_app.Lv3_6.repository.AuthorRepository;
import com.kakaotechcampus.schedule_app.Lv3_6.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public ScheduleWithAuthorIdResponseDto createSchedule(Long authorId, String contents, String password){
        Author findAuthor = authorRepository.findAuthorByIdOrElseThrow(authorId);
        Schedule schedule = new Schedule(authorId, contents, password);
        Schedule savedSchedule = scheduleRepository.createSchedule(schedule);

        return new ScheduleWithAuthorIdResponseDto(savedSchedule.getId(), findAuthor.getId(), schedule.getContents());
    }
}
