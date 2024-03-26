package com.example.spring_boot_todo_app.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_todo_app.domain.entity.User.User;
import com.example.spring_boot_todo_app.domain.entity.User.UserRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/users/{userId}")
    public User getTask(@PathVariable String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("対象が見つかりません。userId: " + userId));
        return user;
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @PutMapping("users/{userId}")
    public void updateUser(@PathVariable String userId, @RequestBody User userDetails) {
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("更新対象が見つかりません。userId: " + userId));

        userDetails.setUserId(userId);
        userRepository.update(userDetails);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("削除対象が見つかりません。userId: " +
                userId));

        userRepository.delete(user);
    }
}
