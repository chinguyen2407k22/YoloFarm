package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name ="daily_task")
public class DailyTask extends Scheduler{

    @Column(name = "time")
    private LocalTime time;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}

