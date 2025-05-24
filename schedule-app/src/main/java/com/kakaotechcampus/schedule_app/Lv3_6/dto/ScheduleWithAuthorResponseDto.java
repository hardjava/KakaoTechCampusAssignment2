package com.kakaotechcampus.schedule_app.Lv3_6.dto;

import com.kakaotechcampus.schedule_app.Lv3_6.entity.ScheduleWithAuthor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ScheduleWithAuthorResponseDto {
    private final Long authorId;
    private final String name;
    private final String email;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static ScheduleWithAuthorResponseDto toDto(ScheduleWithAuthor scheduleWithAuthor){
        return new ScheduleWithAuthorResponseDto(
                scheduleWithAuthor.getAuthorId(),
                scheduleWithAuthor.getName(),
                scheduleWithAuthor.getEmail(),
                scheduleWithAuthor.getContents(),
                scheduleWithAuthor.getCreatedAt(),
                scheduleWithAuthor.getModifiedAt()
        );
    }
}
