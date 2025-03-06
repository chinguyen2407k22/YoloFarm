package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="temperature_manual")
public class TemperatureManual extends TemperatureSetting{

    @Column(name = "sun_shade")
    @Enumerated(EnumType.STRING)
    private State sunsnShade;
}
