# Todo App API ドキュメント

## ローカルサーバー起動

`src/main/java/com/example/spring_boot_todo_app/SpringBootTodoAppApplication.java をrunする`

## ローカル DB 起動

`docker-compose up -d`

## API 仕様

### ログイン API

#### 概要

ログインを行う

#### エンドポイント

POST `/login`

#### サンプルリクエスト

```bash
curl -X POST \
  http://localhost:8888/login \
  -H 'Content-Type: application/json' \
  -d '{"email":"ichiro@example.com","password":"password"}'
```

_※ 以降の/restricted がついた API は JWT 認証が行われる_

### ユーザータスク取得 API

#### 概要

特定のユーザーの全タスクを取得

#### エンドポイント

GET `/tasks`

#### サンプルリクエスト

```bash
curl http://localhost:8888/tasks
```

### ユーザータスク作成 API

#### 概要

新規タスクを作成

#### エンドポイント

POST `/tasks`

#### サンプルリクエスト

```bash
curl -X POST -H "Content-Type: application/json" -d '{
    "taskId": "unique-task-id",
    "user": {"userId": "1"},
    "title": "Task Title",
    "description": "Task Description",
    "status": "NOT_STARTED"
}' http://localhost:8888/tasks
```

### ユーザータスク削除 API

#### 概要

タスクを削除

#### エンドポイント

DELETE `/tasks/:taskId`

#### サンプルリクエスト

```bash
curl -X DELETE http://localhost:8888/tasks/104
```

### ユーザータスク更新 API

#### 概要

タスク内容を更新

#### エンドポイント

PUT `/tasks/:taskId`

#### サンプルリクエスト

```bash
curl -X PUT -H "Content-Type: application/json" -d '{
    "user": {"userId": "3"},
    "title": "更新されたタイトル",
    "description": "更新された詳細",
    "status": "IN_PROGRESS"
}' http://localhost:8888/tasks/103
```

### 全ユーザー取得 API

#### 概要

全ユーザーを取得

#### エンドポイント

GET `/users`

#### サンプルリクエスト

```bash
curl http://localhost:8888/users
```

### ユーザー取得 API

#### 概要

特定ユーザーを取得

#### エンドポイント

GET `/users/:userId`

#### サンプルリクエスト

```bash
curl http://localhost:8888/users/1
```

### ユーザー作成 API

#### 概要

ユーザーを作成

#### エンドポイント

POST `/users`

#### サンプルリクエスト

```bash
curl -X POST \
  http://localhost:8888/users \
  -H 'Content-Type: application/json' \
  -d '{
    "userId": "1",
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password"
  }'
```

### ユーザー削除 API

#### 概要

ユーザーを削除

#### エンドポイント

DELETE `/users/:userId`

#### サンプルリクエスト

```bash
curl -X DELETE http://localhost:8888/users/1
```

### ユーザー更新 API

#### 概要

ユーザー内容を更新

#### エンドポイント

PUT `/users/:userId`

#### サンプルリクエスト

```bash
curl -X PUT \
  http://localhost:8888/users/2 \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "Updated Name",
    "email": "updated@example.com",
    "password": "password"
  }'
```
