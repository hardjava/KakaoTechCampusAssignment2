package com.kakaotechcampus.schedule_app.Lv3_6.dto;

import com.kakaotechcampus.schedule_app.Lv3_6.entity.ScheduleWithAuthor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ScheduleWithAuthorResponseDto {
    private Long authorId;
    private String name;
    private String email;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

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
