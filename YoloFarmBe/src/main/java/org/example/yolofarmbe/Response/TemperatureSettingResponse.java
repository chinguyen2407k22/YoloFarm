package org.example.yolofarmbe.Response;

import lombok.Builder;
import lombok.Data;
import org.example.yolofarmbe.Entity.TemperatureSetting;

@Data
@Builder
public class TemperatureSettingResponse<T extends TemperatureSetting> {
    String message;
    String mode;
    T temperatureSetting;
}
