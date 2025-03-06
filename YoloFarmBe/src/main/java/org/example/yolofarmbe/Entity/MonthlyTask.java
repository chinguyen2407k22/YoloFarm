package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name ="monthly_task")
public class MonthlyTask extends Scheduler {

    @Column(name = "time")
    private LocalTime time;

    @ElementCollection
    @CollectionTable(name = "monthly_days", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "days")
    @Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)
    private List<LocalDate> dateList;
}
