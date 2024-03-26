package com.example.spring_boot_todo_app.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_todo_app.domain.entity.task.Task;
import com.example.spring_boot_todo_app.domain.entity.task.TaskRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

    @GetMapping("/tasks/{taskId}")
    public Task getTask(@PathVariable String taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("対象が見つかりません。taskId: " +
                        taskId));
        return task;
    }

    @PostMapping("/tasks")
    public void createTask(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @PutMapping("/tasks/{userId}/{taskId}")
    public void updateTask(@PathVariable String taskId, @RequestBody Task taskDetails) {
        taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("更新対象が見つかりません。taskId: " +
                        taskId));

        taskDetails.setTaskId(taskId);
        taskRepository.update(taskDetails);
    }

    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable String taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("削除対象が見つかりません。taskId: " +
                        taskId));

        taskRepository.delete(task);
    }
}
