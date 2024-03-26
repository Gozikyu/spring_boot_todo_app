package com.example.spring_boot_todo_app.domain.entity.task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(String id);

    List<Task> findAll();

    Optional<Task> update(Task task);

    void save(Task task);

    void delete(Task task);
}
