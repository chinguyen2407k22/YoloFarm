package org.example.yolofarmbe.Controller;

import org.example.yolofarmbe.Entity.UserAccount;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Exception.UserNotFoundException;
import org.example.yolofarmbe.Request.UserRequest;
import org.example.yolofarmbe.Response.UserResponse;
import org.example.yolofarmbe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserAccountByUsername(@PathVariable String username){
        try {
            UserAccount userAccount = userService.getUserByUsername(username);
            return  ResponseEntity.ok(userAccount);
        } catch (UserNotFoundException e){
            return ResponseEntity.badRequest()
                    .body(UserResponse.builder()
                            .message("Username does not exists!")
                            .userAccount(null)
                            .build());
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserResponse> updateUserAccpunt(@RequestBody UserRequest userRequest, @PathVariable String username){
        try {
            UserResponse userResponse = userService.updateUserAccount(username, userRequest);
            return ResponseEntity.ok(userResponse);
        } catch (UserNotFoundException e){
            return ResponseEntity.badRequest()
                    .body(UserResponse.builder()
                            .message("Username does not exists!")
                            .userAccount(null)
                            .build());
        } catch (ResourceNotFoundException e){
            int id = userRequest.getFarm().getId();
            return ResponseEntity.badRequest()
                    .body(UserResponse.builder()
                            .message("Farm with id " + id + "does not exist!")
                            .userAccount(null)
                            .build());
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<UserResponse> deleteUserAccount(@PathVariable String username){
        try {
            UserResponse userResponse = userService.deleteUserAccount(username);
            return ResponseEntity.ok(userResponse);
        }catch (UserNotFoundException e){
            return ResponseEntity.badRequest()
                    .body(UserResponse.builder()
                            .message("Username does not exists!")
                            .userAccount(null)
                            .build());
        }
    }
}
