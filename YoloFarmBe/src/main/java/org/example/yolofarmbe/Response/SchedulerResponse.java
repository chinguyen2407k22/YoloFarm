package org.example.yolofarmbe.Response;

import lombok.Builder;
import lombok.Data;
import org.example.yolofarmbe.Entity.Scheduler;

@Data
@Builder
public class SchedulerResponse< T extends Scheduler> {
    private String message;
    private String type;
    private T scheduler;
}
