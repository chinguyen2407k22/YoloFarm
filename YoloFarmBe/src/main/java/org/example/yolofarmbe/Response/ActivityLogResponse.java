package org.example.yolofarmbe.Response;

import lombok.Builder;
import lombok.Data;
import org.example.yolofarmbe.Entity.ActivityLog;

@Data
@Builder
public class ActivityLogResponse {
    private String message;
    private ActivityLog activityLog;
}
