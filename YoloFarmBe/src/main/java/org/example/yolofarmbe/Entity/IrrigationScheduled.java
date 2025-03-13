package org.example.yolofarmbe.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "irrigation_scheduled")
public class IrrigationScheduled extends IrrigationSetting{
    @Column(name = "danger_safe_behaviors")
    private String dangerSafeBehavior;

    public String getDangerSafeBehavior() {
        return dangerSafeBehavior;
    }

    public void setDangerSafeBehavior(String dangerSafeBehavior) {
        this.dangerSafeBehavior = dangerSafeBehavior;
    }
}
