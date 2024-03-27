package com.example.spring_boot_todo_app.infra.repository;

import java.time.LocalDateTime;

// TaskRepositoryImpl.java

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.spring_boot_todo_app.domain.entity.task.Task;
import com.example.spring_boot_todo_app.domain.entity.task.TaskRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Task> findById(int id) {
        Task task = entityManager.find(Task.class, id);
        if (task != null && task.getDeletedAt() == null) {
            return Optional.of(task);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Task> findAll(int userId) {
        return entityManager
                .createQuery("SELECT t FROM Task t WHERE t.user.userId = :userId AND t.deletedAt IS NULL",
                        Task.class)
                .setParameter("userId", userId).getResultList();
    }

    @Override
    public Optional<Task> update(Task task) {
        // 更新対象が存在するかどうかを確認
        Optional<Task> foundTask = Optional.ofNullable(entityManager.find(Task.class, task.getTaskId()));
        if (foundTask == null) {
            return null;
        }

        // 更新対象が存在する場合はマージ
        Task mergedUser = entityManager.merge(task);
        return Optional.ofNullable(mergedUser);
    }

    @Override
    public void save(Task task) {
        entityManager.persist(task);
    }

    @Override
    public void delete(Task task) {
        Task t = entityManager.find(Task.class, task.getTaskId());
        t.setDeletedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
    }
}
