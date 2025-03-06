package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="temperature_automated")
public class TemperatureAutomated extends TemperatureSetting{
    @Column(name = "lower")
    private int lower;

    @Column(name = "upper")
    private int upper;

    @Column(name = "send_warning")
    @Enumerated(EnumType.STRING)
    private State sendWarning;
}
