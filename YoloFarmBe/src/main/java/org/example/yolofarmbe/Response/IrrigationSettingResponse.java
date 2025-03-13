package org.example.yolofarmbe.Response;

import lombok.Builder;
import lombok.Data;
import org.example.yolofarmbe.Entity.IrrigationSetting;

@Data
@Builder
public class IrrigationSettingResponse<T extends IrrigationSetting> {
    private String message;
    private String mode;
    private T irrigationSetting;
}
