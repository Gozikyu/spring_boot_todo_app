```
curl -X POST -H "Content-Type: application/json" -d '{
    "taskId": "104",
    "userId": "3",
    "title": "新しいタスク",
    "description": "新しいタスクの詳細",
    "status": "NOT_STARTED"
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
