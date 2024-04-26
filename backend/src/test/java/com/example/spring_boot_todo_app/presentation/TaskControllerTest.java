package com.example.spring_boot_todo_app.presentation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring_boot_todo_app.domain.entity.User.User;
import com.example.spring_boot_todo_app.domain.entity.task.Task;
import com.example.spring_boot_todo_app.domain.entity.task.TaskRepository;

@SpringBootTest
public class TaskControllerTest {
    // controllerのテストは本来は統合テストとして、モックを使わず（=DBと実際に繋いで）実施すべきだが、今回はmockitの挙動確認のためモック化している
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskController taskController;

    private User savedUser;

    @Test
    void testGetTasks() {
        this.savedUser = new User(1, "テスト太郎", "test@example.com", "password");

        List<Task> mockedTasks = new ArrayList<>();
        mockedTasks.add(new Task(savedUser, "タスク1", "タスク1の本文", "NOT_STARTED"));
        mockedTasks.add(new Task(savedUser, "タスク2", "タスク2の本文", "NOT_STARTED"));

        when(this.taskRepository.findAll(1)).thenReturn(mockedTasks);

        List<Task> response = taskController.getTasks(1);

        assertEquals(mockedTasks.size(), response.size());
        for (int i = 0; i < mockedTasks.size(); i++) {
            Task eachTask = response.get(i);
            assertEquals(mockedTasks.get(i).getTaskId(), eachTask.getTaskId());
            assertEquals(mockedTasks.get(i).getTitle(), eachTask.getTitle());
            assertEquals(mockedTasks.get(i).getDescription(), eachTask.getDescription());
            assertEquals(mockedTasks.get(i).getStatus(), eachTask.getStatus());
        }
    }
}
