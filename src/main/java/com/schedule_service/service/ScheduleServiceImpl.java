package com.schedule_service.service;

import com.schedule_service.domain.Schedule;
import com.schedule_service.domain.User;
import com.schedule_service.dto.ScheduleRegistrationRequest;
import com.schedule_service.dto.ScheduleResponse;
import com.schedule_service.dto.ScheduleUpdateRequest;
import com.schedule_service.exception.ScheduleNotFoundException;
import com.schedule_service.exception.UserNotFoundException;
import com.schedule_service.repository.ScheduleRepository;
import com.schedule_service.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public ScheduleResponse createSchedule(Long userId, ScheduleRegistrationRequest request) {

        User user = getUserById(userId);

        Schedule schedule = Schedule.of(
                user,
                request.title(),
                request.description(),
                request.startDateTime(),
                request.endDateTime(),
                request.type());

        user.addSchedule(schedule);

        return ScheduleResponse.from(scheduleRepository.save(schedule));
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Id : " + userId + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleResponse> findAllSchedulesAtUser(Long userId) {
        getUserById(userId);
        return scheduleRepository.findAllByUserId(userId).stream()
                .map(ScheduleResponse::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ScheduleResponse findScheduleById(Long scheduleId, Long userId) {
        Schedule schedule = getScheduleByIdAndUserId(scheduleId, userId);
        return ScheduleResponse.from(schedule);
    }


    @Override
    public ScheduleResponse update(Long scheduleId, Long userId, ScheduleUpdateRequest request) {
        Schedule schedule = getScheduleByIdAndUserId(scheduleId, userId);

        schedule.update(
                request.title(),
                request.description(),
                request.startDateTime(),
                request.endDateTime());

        return ScheduleResponse.from(schedule);
    }

    @Override
    public void delete(Long scheduleId, Long userId) {
        Schedule schedule = getScheduleByIdAndUserId(scheduleId, userId);  // 존재 및 권한 확인
        scheduleRepository.delete(schedule);
    }

    private Schedule getScheduleByIdAndUserId(Long scheduleId, Long userId) {
        return scheduleRepository.findByIdAndUserId(scheduleId, userId)
                .orElseThrow(() -> new ScheduleNotFoundException(
                        "Schedule Id : " + scheduleId + " not found for user : " + userId));
    }
}
