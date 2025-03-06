package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="light_automated")
public class LightAutomated extends LightSetting{
    @Column(name = "send_warning")
    @Enumerated(EnumType.STRING)
    private State sendWarning;

    @Column(name = "min")
    private int min;

    @Column(name = "max")
    private int max;

    @Column(name = "upper")
    private int upper;

    @Column(name = "lower")
    private int lower;
}
