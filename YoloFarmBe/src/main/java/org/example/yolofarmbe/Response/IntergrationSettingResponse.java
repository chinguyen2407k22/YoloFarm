package org.example.yolofarmbe.Response;

import lombok.Builder;
import lombok.Data;
import org.example.yolofarmbe.Entity.IntergrationSetting;

@Data
@Builder
public class IntergrationSettingResponse {
    private String message;
    private IntergrationSetting intergrationSetting;
}
