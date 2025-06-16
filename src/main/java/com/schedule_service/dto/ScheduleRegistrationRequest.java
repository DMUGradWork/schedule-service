package com.schedule_service.dto;

import com.schedule_service.domain.ScheduleType;
import java.time.LocalDateTime;

public record ScheduleRegistrationRequest(String title,
                                          LocalDateTime startDateTime,
                                          LocalDateTime endDateTime,
                                          String description,
                                          ScheduleType type) {

}
