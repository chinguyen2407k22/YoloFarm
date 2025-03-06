package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

import java.time.Duration;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "scheduler")
public abstract class Scheduler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name ="duration")
    private Duration duration;

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = Duration.ofSeconds(duration);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
