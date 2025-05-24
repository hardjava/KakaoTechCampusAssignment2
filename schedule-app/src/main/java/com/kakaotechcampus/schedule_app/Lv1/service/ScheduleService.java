//package com.kakaotechcampus.schedule_app.Lv1.service;
//
//import com.kakaotechcampus.schedule_app.Lv1.dto.ScheduleResponseDto;
//import com.kakaotechcampus.schedule_app.Lv1.dto.ScheduleWithDateResponseDto;
//import com.kakaotechcampus.schedule_app.Lv1.entity.Schedule;
//import com.kakaotechcampus.schedule_app.Lv1.repository.ScheduleRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ScheduleService {
//    private final ScheduleRepository scheduleRepository;
//
//    public ScheduleResponseDto save(String username, String contents, String password){
//        Schedule schedule = new Schedule(username, contents, password);
//        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);
//
//        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getUsername(), savedSchedule.getContents());
//    }
//
//    public List<ScheduleWithDateResponseDto> findAll(String username, LocalDate modifiedAt){
//        return scheduleRepository.findAll(username, modifiedAt)
//                .stream()
//                .map(ScheduleWithDateResponseDto::toDto)
//                .toList();
//    }
//
//    public ScheduleWithDateResponseDto findById(Long id){
//        Schedule savedSchedule = scheduleRepository.findByIdOrElseThrow(id);
//
//        return new ScheduleWithDateResponseDto(
//                savedSchedule.getId(),
//                savedSchedule.getUsername(),
//                savedSchedule.getContents(),
//                savedSchedule.getCreatedAt(),
//                savedSchedule.getModifiedAt()
//        );
//    }
//
//    @Transactional
//    public ScheduleWithDateResponseDto update(Long id, String password, String username, String contents){
//        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
//
//        if (!findSchedule.getPassword().equals(password)){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Does Not Match Password");
//        }
//
//        if (username == null && contents == null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nothing to Update");
//        }
//
//        if (username != null){
//            findSchedule.setUsername(username);
//        }
//
//        if (contents != null){
//            findSchedule.setContents(contents);
//        }
//
//        Schedule updatedSchedule = scheduleRepository.update(findSchedule);
//
//        return new ScheduleWithDateResponseDto(
//                updatedSchedule.getId(),
//                updatedSchedule.getUsername(),
//                updatedSchedule.getContents(),
//                updatedSchedule.getCreatedAt(),
//                updatedSchedule.getModifiedAt()
//        );
//    }
//
//    @Transactional
//    public void delete(Long id, String password){
//        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
//
//        if (!findSchedule.getPassword().equals(password)){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Does Not Match Password");
//        }
//
//        scheduleRepository.delete(id);
//    }
//}
