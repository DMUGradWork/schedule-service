package com.schedule_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Schedule> schedules = new ArrayList<>();

    public void addSchedule(Schedule schedule) {
        if (!schedules.contains(schedule)) {
            schedules.add(schedule);
        }
    }
    void removeSchedule(Schedule schedule) {
        schedules.remove(schedule);
    }

    public int getScheduleCount() {
        return schedules.size();
    }

    public boolean hasSchedule(Schedule schedule) {
        return schedules.contains(schedule);
    }
}

