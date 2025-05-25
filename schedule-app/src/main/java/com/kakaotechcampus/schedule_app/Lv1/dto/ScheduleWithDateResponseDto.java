//package com.kakaotechcampus.schedule_app.Lv1.dto;
//
//import com.kakaotechcampus.schedule_app.Lv1.entity.Schedule;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//
//import java.time.LocalDateTime;
//
//@AllArgsConstructor
//@Getter
//public class ScheduleWithDateResponseDto {
//    private final Long id;
//    private final String username;
//    private final String contents;
//
//    private final LocalDateTime createdAt;
//    private final LocalDateTime modifiedAt;
//
//    public static ScheduleWithDateResponseDto toDto(Schedule schedule){
//        return new ScheduleWithDateResponseDto(
//                schedule.getId(),
//                schedule.getUsername(),
//                schedule.getContents(),
//                schedule.getCreatedAt(),
//                schedule.getModifiedAt()
//        );
//    }
//}
