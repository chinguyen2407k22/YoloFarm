package org.example.yolofarmbe.Response;

import lombok.Builder;
import lombok.Data;
import org.example.yolofarmbe.Entity.Reminder;

@Data
@Builder
public class ReminderResponse {
    private String message;
    private Reminder reminder;
}
