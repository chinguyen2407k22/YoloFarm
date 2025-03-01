package org.example.yolofarmbe.Request;
import lombok.Data;
import org.example.yolofarmbe.Entity.Farm;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Farm farm;
}