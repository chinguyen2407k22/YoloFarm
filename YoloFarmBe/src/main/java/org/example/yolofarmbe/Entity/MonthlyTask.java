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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "monthly_days", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "days")
    private List<LocalDate> dateList;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public List<LocalDate> getDateList() {
        return dateList;
    }

    public void setDateList(List<LocalDate> dateList) {
        this.dateList = dateList;
    }
}
