package org.example.yolofarmbe.Request;

import lombok.Data;
import org.example.yolofarmbe.Entity.Farm;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private Farm farm;
}
