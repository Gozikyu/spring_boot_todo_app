package com.example.spring_boot_todo_app.infra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    private String taskId;
    private String userId;
    private String title;
    private String description;
    private String status;

    public Task() {
    }

    public Task(String taskId, String userId, String title, String description, String status) {
        this.taskId = taskId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    // taskIdのgetterとsetter
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    // userIdのgetterとsetter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // titleのgetterとsetter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // descriptionのgetterとsetter
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // statusのgetterとsetter
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}