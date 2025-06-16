package com.schedule_service.controller;

import com.schedule_service.dto.ScheduleCard;
import com.schedule_service.dto.ScheduleRegistrationRequest;
import com.schedule_service.dto.ScheduleResponse;
import com.schedule_service.dto.ScheduleUpdateRequest;
import com.schedule_service.service.ScheduleService;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/schedule-service")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/{userId}")
    public ResponseEntity<ScheduleResponse> addSchedule(
            @PathVariable Long userId,
            @RequestBody ScheduleRegistrationRequest request) {

        ScheduleResponse added = scheduleService.createSchedule(userId, request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userId}/{scheduleId}")
                .buildAndExpand(added.id())
                .toUri();

        return ResponseEntity.created(location).body(added);
    }

    @GetMapping("/{userId}/schedules")
    public ResponseEntity<List<ScheduleResponse>> getSchedulesForUser(
            @PathVariable Long userId) {
        List<ScheduleResponse> allSchedulesAtUserId = scheduleService.findAllSchedulesAtUser(userId);
        return ResponseEntity.ok(allSchedulesAtUserId);
    }

    @GetMapping("/{userId}/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getScheduleDetails(
            @PathVariable Long userId,
            @PathVariable Long scheduleId) {
        ScheduleResponse allSchedulesAtUserId = scheduleService.findScheduleById(scheduleId,userId);
        return ResponseEntity.ok(allSchedulesAtUserId);
    }


    @PatchMapping("/{userId}/schedules/{scheduleId}")
     public ResponseEntity<ScheduleResponse> updateSchedule(
            @PathVariable Long userId,
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest scheduleUpdateRequest) {

        ScheduleResponse updated = scheduleService.update(scheduleId,userId,scheduleUpdateRequest);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{userId}/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long userId,
            @PathVariable Long scheduleId){
        scheduleService.delete(scheduleId,userId);
        return ResponseEntity.noContent().build();
    }
}
