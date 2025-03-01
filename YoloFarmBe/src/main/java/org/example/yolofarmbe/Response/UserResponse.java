package org.example.yolofarmbe.Response;

import lombok.Builder;
import lombok.Data;
import org.example.yolofarmbe.Entity.UserAccount;

@Data
@Builder
public class UserResponse {
    private String message;
    private UserAccount userAccount;
}