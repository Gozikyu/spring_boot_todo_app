package com.example.spring_boot_todo_app.domain.entity.task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(int taskId);

    List<Task> findAll(int userId);

    Optional<Task> update(Task task);

    Task save(Task task);

    void delete(Task task);
}
