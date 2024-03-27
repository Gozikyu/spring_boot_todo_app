package com.example.spring_boot_todo_app.infra.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.spring_boot_todo_app.domain.entity.User.UserRepository;
import com.example.spring_boot_todo_app.domain.entity.User.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        List<User> resultList = entityManager
                .createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                .setParameter("email", email).setParameter("password", password).getResultList();

        if (resultList.size() > 2) {
            new RuntimeException("メールアドレスとパスワードの組が複数あります");
        }
        if (resultList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(resultList.get(0));
    }

    @Override
    public Optional<User> update(User user) {
        // 更新対象が存在するかどうかを確認
        Optional<User> foundUser = Optional.ofNullable(entityManager.find(User.class, user.getUserId()));
        if (foundUser == null) {
            return null;
        }

        // 更新対象が存在する場合はマージ
        User mergedUser = entityManager.merge(user);
        return Optional.ofNullable(mergedUser);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        User u = entityManager.find(User.class, user.getUserId());
        u.setDeletedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
    }
}
