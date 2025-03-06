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

}
