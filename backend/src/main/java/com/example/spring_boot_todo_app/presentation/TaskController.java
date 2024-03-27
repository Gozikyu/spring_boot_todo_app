package com.example.spring_boot_todo_app.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_todo_app.domain.entity.task.Task;
import com.example.spring_boot_todo_app.domain.entity.task.TaskRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/{userId}/tasks")
    public List<Task> getTasks(@PathVariable int userId) {
        List<Task> tasks = taskRepository.findAll(userId);
        return tasks;
    }

    @GetMapping("/{userId}/tasks/{taskId}")
    public Task getTask(@PathVariable String userId, @PathVariable int taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("対象が見つかりません。taskId: " +
                        taskId));
        return task;
    }

    @PostMapping("/{userId}/tasks")
    public void createTask(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @PutMapping("/{userId}/tasks/{taskId}")
    public void updateTask(@RequestBody Task taskDetails) {
        taskRepository.findById(taskDetails.getTaskId())
                .orElseThrow(() -> new RuntimeException("更新対象が見つかりません。taskId: " +
                        String.valueOf(taskDetails.getTaskId())));

        taskRepository.update(taskDetails);
    }

    @DeleteMapping("/{userId}/tasks/{taskId}")
    public void deleteTask(@PathVariable int taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("削除対象が見つかりません。taskId: " +
                        taskId));

        taskRepository.delete(task);
    }
}
