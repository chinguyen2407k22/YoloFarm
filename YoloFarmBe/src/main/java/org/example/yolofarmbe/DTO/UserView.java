package org.example.yolofarmbe.DTO;
import org.example.yolofarmbe.Entity.Farm;

import java.time.LocalDateTime;

public interface UserView {
    String getUsername();
    String getHashPassword();
    String getFirstname();
    String getLastname();
    String getEmail();
    FarmIdView getFarm();
}
