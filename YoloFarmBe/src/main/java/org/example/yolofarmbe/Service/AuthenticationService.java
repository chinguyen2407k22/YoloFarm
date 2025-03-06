package org.example.yolofarmbe.Service;

import lombok.RequiredArgsConstructor;
import org.example.yolofarmbe.Entity.Farm;
import org.example.yolofarmbe.Entity.UserAccount;
import org.example.yolofarmbe.Exception.IncorrectPasswordException;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Exception.UserAlreadyExistsException;
import org.example.yolofarmbe.Exception.UserNotFoundException;
import org.example.yolofarmbe.Repository.FarmRepository;
import org.example.yolofarmbe.Repository.UserAccountRepository;
import org.example.yolofarmbe.Request.LoginRequest;
import org.example.yolofarmbe.Request.RegisterRequest;
import org.example.yolofarmbe.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.example.yolofarmbe.Entity.UserRole.USER;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FarmRepository farmRepository;

    public UserResponse registerUser(RegisterRequest request) {

        // Kiểm tra username đã tồn tại chưa
        if (userAccountRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException();
        }

        // Mã hóa password trước khi lưu
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Farm farm = null;
        if(request.getFarm() != null) {
            int id = request.getFarm().getId();
            farm = farmRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Farm with id "+ id + "not exists"));
        }
        // Tạo user mới
        UserAccount newUser = UserAccount.builder()
                .username(request.getUsername())
                .hashPassword((encodedPassword))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .farm(farm)
                .userRole(USER)
                .build();

        userAccountRepository.save(newUser);

        return UserResponse.builder()
                .message("User registered successfully")
                .userAccount(newUser)
                .build();
    }

    public UserResponse loginUser(LoginRequest loginRequest){
        UserAccount userAccount = userAccountRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(()->new UserNotFoundException());

        if (passwordEncoder.matches(loginRequest.getPassword(),userAccount.getHashPassword())){
            return UserResponse.builder()
                    .message("User login successfully")
                    .userAccount(userAccount)
                    .build();
        }
        else throw new IncorrectPasswordException();

    }
}
