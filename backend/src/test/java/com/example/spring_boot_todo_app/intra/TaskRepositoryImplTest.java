package com.example.spring_boot_todo_app.intra;

import com.example.spring_boot_todo_app.domain.entity.User.User;
import com.example.spring_boot_todo_app.domain.entity.User.UserRepository;
import com.example.spring_boot_todo_app.domain.entity.task.Task;
import com.example.spring_boot_todo_app.domain.entity.task.TaskRepository;

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@SpringBootTest
@Transactional
class TaskRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EntityManager entityManager;

    private User savedUser;

    @BeforeEach
    void setUp() {
        User user = new User(1, "テスト太郎", "test@example.com", "password");
        this.savedUser = userRepository.save(user);
    }

    @Test
    void update_更新対象が存在する場合は更新後のタスクが返される() {
        Task task = new Task(savedUser, "タスク1", "タスク1の本文", "NOT_STARTED");
        Task savedTask = taskRepository.save(task);

        savedTask.setTitle("更新されたタスク");
        Optional<Task> updatedTaskOptional = taskRepository.update(savedTask);

        assertTrue(updatedTaskOptional.isPresent());
        Task updatedTask = updatedTaskOptional.get();
        assertEquals("更新されたタスク", updatedTask.getTitle());
    }

    @Test
    void update_更新対象が存在しない場合はemptyが返される() {
        Task task = new Task(savedUser, "タスク1", "タスク1の本文", "NOT_STARTED");
        Task savedTask = taskRepository.save(task);

        entityManager.remove(savedTask);

        Optional<Task> updatedTaskOptional = taskRepository.update(savedTask);
        assertFalse(updatedTaskOptional.isPresent());
    }
}
