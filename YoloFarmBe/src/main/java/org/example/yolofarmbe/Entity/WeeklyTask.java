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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "weekly_days", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "days")
    private List<DayOfWeek> dateList;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public List<DayOfWeek> getDateList() {
        return dateList;
    }

    public void setDateList(List<DayOfWeek> dateList) {
        this.dateList = dateList;
    }
}
