package org.example.yolofarmbe.Controller;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.example.yolofarmbe.Entity.UserAccount;
import org.example.yolofarmbe.Exception.IncorrectPasswordException;
import org.example.yolofarmbe.Exception.UserAlreadyExistsException;
import org.example.yolofarmbe.Exception.UserNotFoundException;
import org.example.yolofarmbe.Request.LoginRequest;
import org.example.yolofarmbe.Request.RegisterRequest;
import org.example.yolofarmbe.Response.UserResponse;
import org.example.yolofarmbe.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterRequest request) {
        try {
            UserResponse response = authenticationService.registerUser(request);
            return ResponseEntity.ok(response);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest()
                    .body(UserResponse.builder()
                            .message("Username already exists")
                            .userView(null)
                            .build());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody LoginRequest request) {
        try {
            UserResponse response = authenticationService.loginUser(request);
            return ResponseEntity.ok(response);
        }catch (UserNotFoundException e) {
            return ResponseEntity.badRequest()
                    .body(UserResponse.builder()
                            .message("Username does not exist!")
                            .userView(null)
                            .build());
        }catch (IncorrectPasswordException e){
            return ResponseEntity.badRequest()
                    .body(UserResponse.builder()
                            .message("Password Incorrect!")
                            .userView(null)
                            .build());
        }
    }

}
