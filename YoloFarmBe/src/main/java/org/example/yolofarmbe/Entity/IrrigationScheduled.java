package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "irrigation_scheduled")
public class IrrigationScheduled extends IrrigationSetting{
    @Column(name = "danger_safe_behaviors")
    private String dangerSafeBehavior;

    @OneToOne()
    @JoinColumn(name = "scheduler_id",referencedColumnName = "id")
    private Scheduler scheduler;
}
