package org.example.yolofarmbe.Response;

import lombok.Builder;
import lombok.Data;
import org.example.yolofarmbe.Entity.Device;

@Data
@Builder
public class DeviceResponse {
    String message;
    Device device;
}
