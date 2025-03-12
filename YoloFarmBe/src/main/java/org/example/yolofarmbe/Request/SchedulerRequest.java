package org.example.yolofarmbe.Request;

import lombok.Data;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class SchedulerRequest {
    private long duration;
    private LocalTime time;
    private List<LocalDate> days; //for montly task
    private List<DayOfWeek> dayOfWeeks; //for weekly task
}
