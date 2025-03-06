package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "light_scheduled")
public class LightScheduled extends LightSetting{
    @Column(name = "send_warning")
    @Enumerated(EnumType.STRING)
    private State sendWarning;

    @OneToOne()
    @JoinColumn(name = "scheduler_id",referencedColumnName = "id")
    private Scheduler scheduler;
}
