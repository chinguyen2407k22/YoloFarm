package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.time.LocalTime;

@Entity
@Table(name = "activity_log")
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "category", length = 50)
    private String category;

    @Nationalized
    @Column(name = "title")
    private String title;

    @Nationalized
    @Column(name = "mode", length = 50)
    private String mode;

    @Column(name = "log_time")
    private LocalTime logTime;

    @ManyToOne()
    @JoinColumn(name = "farm_id")
    private Farm farm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public LocalTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalTime logTime) {
        this.logTime = logTime;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

}