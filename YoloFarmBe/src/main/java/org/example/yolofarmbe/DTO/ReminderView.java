package org.example.yolofarmbe.DTO;

import org.example.yolofarmbe.Entity.ReminderId;

import java.time.LocalDateTime;

public interface ReminderView {
    ReminderId getId();
    String getTitle();
    LocalDateTime getReminderTime();
    String getReminderDescription();
    Boolean getIsDone();
}

