package com.example.spring_boot_todo_app.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(int id);

    List<User> findAll();

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> update(User user);

    User save(User user);

    void delete(User user);
}
