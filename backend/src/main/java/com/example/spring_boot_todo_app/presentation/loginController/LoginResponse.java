package com.example.spring_boot_todo_app.presentation.loginController;

public class LoginResponse {
    private String userId;

    public LoginResponse(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
