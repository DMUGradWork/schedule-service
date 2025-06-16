package com.schedule_service.dto;

import com.schedule_service.domain.Schedule;
import com.schedule_service.domain.ScheduleType;
import java.time.LocalDateTime;

public record ScheduleResponse(Long id,
                               String title,
                               String description,
                               LocalDateTime startDateTime,
                               LocalDateTime endDateTime,
                               ScheduleType scheduleType) {

    public static ScheduleResponse from(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getType());
    }
}
