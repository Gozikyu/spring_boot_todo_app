package com.example.spring_boot_todo_app.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_todo_app.infra.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
}
