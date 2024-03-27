package com.example.spring_boot_todo_app.presentation.loginController;

public class LoginResponse {
    private int userId;

    public LoginResponse(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
