package com.example.spring_boot_todo_app.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(String id);

    List<User> findAll();

    Optional<User> update(User user);

    void save(User user);

    void delete(User user);
}
