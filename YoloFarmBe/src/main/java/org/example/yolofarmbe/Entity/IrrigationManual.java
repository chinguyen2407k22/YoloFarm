package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "irrigation_manual")
public class IrrigationManual extends IrrigationSetting{
    @Column(name = "wartering")
    @Enumerated(EnumType.STRING)
    private State watering;

    public State getWatering() {
        return watering;
    }

    public void setWatering(State watering) {
        this.watering = watering;
    }
}
