package com.schedule_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private LocalDateTime created;
    private LocalDateTime modified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ScheduleType type;

    private Schedule (String title,
                      String description,
                      LocalDateTime startTime,
                      LocalDateTime endTime,
                      User creator,
                      ScheduleType type) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = creator;
        this.type = type;

        LocalDateTime now = LocalDateTime.now();
        this.created = now;
        this.modified = now;
    }

    public static Schedule of(User creator,
                              String title,
                              String description,
                              LocalDateTime startTime,
                              LocalDateTime endTime,
                              ScheduleType type) {
        return new Schedule(title, description, startTime, endTime, creator, type);
    }

    public void update(String newTitle,
                       String newDescription,
                       LocalDateTime newStartTime,
                       LocalDateTime newEndTime) {
        this.title = newTitle;
        this.description = newDescription;
        this.startTime = newStartTime;
        this.endTime = newEndTime;
        this.modified = LocalDateTime.now();
    }

}