package org.example.yolofarmbe.Request;

import lombok.Data;
import org.example.yolofarmbe.Entity.Reminder;
import org.example.yolofarmbe.Entity.ReminderId;

@Data
public class ReminderRequest {
    private ReminderId reminderId;
    private Reminder reminder;
}
