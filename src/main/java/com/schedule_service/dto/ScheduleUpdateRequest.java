package com.schedule_service.dto;

import java.time.LocalDateTime;

public record ScheduleUpdateRequest(String title,
                                    String description,
                                    LocalDateTime startDateTime,
                                    LocalDateTime endDateTime) {
}
