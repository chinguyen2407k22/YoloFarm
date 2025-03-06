package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "irrigation_manual")
public class IrrigationManual extends IrrigationSetting{
    @Column(name = "wartering")
    @Enumerated(EnumType.STRING)
    private State watering;
}
