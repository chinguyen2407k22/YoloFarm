package org.example.yolofarmbe.Response;

import lombok.Builder;
import lombok.Data;
import org.example.yolofarmbe.Entity.Farm;

@Data
@Builder
public class FarmResponse {
    private String message;
    private Farm farm;
}
