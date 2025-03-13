package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "light_manual")
public class LightManual extends LightSetting{
    @Column(name = "turn_on")
    @Enumerated(EnumType.STRING)
    private State turnOn;

    public State getTurnOn() {
        return turnOn;
    }

    public void setTurnOn(State turnOn) {
        this.turnOn = turnOn;
    }
}
