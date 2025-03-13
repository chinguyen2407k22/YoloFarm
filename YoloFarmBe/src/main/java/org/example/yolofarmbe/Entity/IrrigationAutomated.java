package org.example.yolofarmbe.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "irrigation_automated")
public class IrrigationAutomated extends IrrigationSetting{
    @Column(name = "moisture_level")
    private String moistureLevel;

    @Column(name = "danger_safe_behaviors")
    private String dangerSafeBehavior;

    public String getMoistureLevel() {
        return moistureLevel;
    }

    public void setMoistureLevel(String moistureLevel) {
        this.moistureLevel = moistureLevel;
    }

    public String getDangerSafeBehavior() {
        return dangerSafeBehavior;
    }

    public void setDangerSafeBehavior(String dangerSafeBehavior) {
        this.dangerSafeBehavior = dangerSafeBehavior;
    }
}
