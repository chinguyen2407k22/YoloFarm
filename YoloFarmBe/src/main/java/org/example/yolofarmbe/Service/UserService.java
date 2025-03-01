package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.Entity.Farm;
import org.example.yolofarmbe.Entity.UserAccount;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Exception.UserNotFoundException;
import org.example.yolofarmbe.Repository.FarmRepository;
import org.example.yolofarmbe.Repository.UserAccountRepository;
import org.example.yolofarmbe.Request.UserRequest;
import org.example.yolofarmbe.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FarmRepository farmRepository;

    public UserAccount getUserByUsername(String username){
        UserAccount userAccount = userAccountRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException());
        return  userAccount;
    }

    public UserResponse updateUserAccount(String username, UserRequest request){
        UserAccount userAccount = userAccountRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException());
        if (request.getPassword() != null){
            userAccount.setHashPassword(passwordEncoder.encode(request.getPassword()));
        }
        if(request.getFirstname()!= null){
            userAccount.setFirstname(request.getFirstname());
        }
        if (request.getLastname() != null){
            userAccount.setLastname(request.getLastname());
        }
        if (request.getFarm() != null){
            int id = request.getFarm().getId();
            Farm farm = farmRepository.findById(id)
                    .orElseThrow(()->new ResourceNotFoundException("Farm with id " + id + "does not exist!" ));
            userAccount.setFarm(farm);
        }
        userAccountRepository.save(userAccount);
        return UserResponse.builder()
                .message("Update user account successfully!")
                .userAccount(userAccount)
                .build();
    }

    public UserResponse deleteUserAccount(String username){
        UserAccount userAccount = userAccountRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException());
        userAccountRepository.delete(userAccount);
        return UserResponse.builder()
                .message("Delete user account successfully!")
                .userAccount(userAccount)
                .build();
    }
}
