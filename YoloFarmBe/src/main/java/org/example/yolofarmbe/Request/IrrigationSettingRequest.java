package org.example.yolofarmbe.Request;

import lombok.Data;
import org.example.yolofarmbe.Entity.Farm;
import org.example.yolofarmbe.Entity.State;

@Data
public class IrrigationSettingRequest {
    private Farm farm;
    private String moistureLevel;
    private String dangerSafeBehavior;
    private State watering;
}
