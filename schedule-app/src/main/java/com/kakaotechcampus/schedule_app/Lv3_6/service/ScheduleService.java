package com.kakaotechcampus.schedule_app.Lv3_6.service;

import com.kakaotechcampus.schedule_app.Lv3_6.dto.ScheduleResponseDto;
import com.kakaotechcampus.schedule_app.Lv3_6.dto.ScheduleWithAuthorIdResponseDto;
import com.kakaotechcampus.schedule_app.Lv3_6.dto.ScheduleWithAuthorResponseDto;
import com.kakaotechcampus.schedule_app.Lv3_6.entity.Author;
import com.kakaotechcampus.schedule_app.Lv3_6.entity.Schedule;
import com.kakaotechcampus.schedule_app.Lv3_6.repository.AuthorRepository;
import com.kakaotechcampus.schedule_app.Lv3_6.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

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

    @Transactional
    public List<ScheduleWithAuthorResponseDto> findAll(Long authorId, LocalDate modified_at){
        if (authorId != null){
            Author findAuthor = authorRepository.findAuthorByIdOrElseThrow(authorId);
        }

        return scheduleRepository.findAll(authorId, modified_at)
                .stream()
                .map(ScheduleWithAuthorResponseDto::toDto)
                .toList();
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, String password, String contents){
        Schedule savedSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        if (!password.equals(savedSchedule.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Does Not Match Password");
        }

        savedSchedule.setContents(contents);

        Schedule updatedSchedule = scheduleRepository.updateSchedule(savedSchedule);

        return new ScheduleResponseDto(
                updatedSchedule.getId(),
                updatedSchedule.getAuthorId(),
                updatedSchedule.getContents(),
                updatedSchedule.getCreatedAt(),
                updatedSchedule.getModifiedAt()
        );
    }
}
