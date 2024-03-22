```
curl -X POST -H "Content-Type: application/json" -d '{
    "taskId": "unique-task-id",
    "user": {"userId": "1"},
    "title": "Task Title",
    "description": "Task Description",
    "status": "pending"
}' http://localhost:8080/tasks
```

```
curl -X PUT -H "Content-Type: application/json" -d '{
    "title": "更新されたタイトル",
    "description": "更新された詳細",
    "status": "IN_PROGRESS"
}' http://localhost:8080/tasks/104
```

```
curl -X DELETE http://localhost:8080/tasks/104
```
