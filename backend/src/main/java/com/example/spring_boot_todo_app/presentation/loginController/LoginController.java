package com.example.spring_boot_todo_app.presentation.loginController;

import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_todo_app.domain.entity.User.User;
import com.example.spring_boot_todo_app.domain.entity.User.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // 非常に簡易的なログインAPI
    @PostMapping(value = "/login", produces = "application/json")
    @CrossOrigin
    public LoginResponse login(@RequestBody LoginRequest param) {
        String email = param.getEmail();
        String password = param.getPassword();

        Optional<User> userOptional = userRepository.findByEmailAndPassword(email, password);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String userId = user.getUserId();

            return new LoginResponse(userId);
        } else {
            throw new RuntimeException("存在しないユーザーです");
        }
    }

}
