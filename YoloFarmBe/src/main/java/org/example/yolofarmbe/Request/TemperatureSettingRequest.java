package org.example.yolofarmbe.Request;

import lombok.Data;
import org.example.yolofarmbe.Entity.Farm;
import org.example.yolofarmbe.Entity.State;

@Data
public class TemperatureSettingRequest {
    private Farm farm;
    private int lower;
    private int upper;
    private State sendWarning;
    private State sunShade;
}
