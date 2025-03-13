package org.example.yolofarmbe.Request;

import lombok.Data;
import org.example.yolofarmbe.Entity.Farm;
import org.example.yolofarmbe.Entity.Scheduler;
import org.example.yolofarmbe.Entity.State;

@Data
public class LightSettingRequest {
    Farm farm;
    State sendWarning;
    int min;
    int max;
    int upper;
    int lower;
    State turnOn;
}
