package org.example.yolofarmbe.DTO;

import java.time.LocalTime;

public interface ActivityLogView {
    int getId();
    String getTitle();
    String getCategory();
    String getMode();
    LocalTime getLogTime();
    UsernameView getUserAccount();
}
