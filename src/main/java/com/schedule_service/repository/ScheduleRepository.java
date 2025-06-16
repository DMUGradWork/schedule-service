package com.schedule_service.repository;

import com.schedule_service.domain.Schedule;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByUserId(Long userId);
    Optional<Schedule> findByIdAndUserId(Long scheduleId, Long userId);
}
