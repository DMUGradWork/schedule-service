package com.schedule_service.dto;

import com.schedule_service.domain.Schedule;
import com.schedule_service.domain.ScheduleType;
import java.time.LocalDateTime;

public record ScheduleCard(String title,
                           String description,
                           LocalDateTime startDate,
                           LocalDateTime endDate,
                           ScheduleType scheduleType) {

    public static ScheduleCard of(Schedule schedule) {
        return new ScheduleCard(
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getType()
        );
    }

}
