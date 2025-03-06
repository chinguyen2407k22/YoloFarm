package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name ="weekly_task")
public class WeeklyTask extends Scheduler{
    @Column(name = "time")
    private LocalTime time;

    @ElementCollection
    @CollectionTable(name = "weekly_days", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "days")
    @Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)
    private List<DayOfWeek> dateList;
}
