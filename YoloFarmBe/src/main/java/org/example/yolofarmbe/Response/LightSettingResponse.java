package org.example.yolofarmbe.Response;

import lombok.Builder;
import lombok.Data;
import org.example.yolofarmbe.Entity.LightSetting;

@Data
@Builder
public class LightSettingResponse<T extends LightSetting> {
    private String message;
    private String mode;
    private T lightSetting;
}
