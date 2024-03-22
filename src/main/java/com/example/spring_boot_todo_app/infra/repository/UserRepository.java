package com.example.spring_boot_todo_app.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_todo_app.infra.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findById(String id);
}
