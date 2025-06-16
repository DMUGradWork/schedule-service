package com.schedule_service.service;

import com.schedule_service.dto.ScheduleRegistrationRequest;
import com.schedule_service.dto.ScheduleResponse;
import com.schedule_service.dto.ScheduleUpdateRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    ScheduleResponse createSchedule(Long userId, ScheduleRegistrationRequest request);
    List<ScheduleResponse> findAllSchedulesAtUser(Long userId);
    ScheduleResponse findScheduleById(Long scheduleId, Long userId);
    ScheduleResponse update(Long scheduleId, Long userId, ScheduleUpdateRequest request);
    void delete(Long scheduleId, Long userId);
}
