CREATE TABLE users (
    user_id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    deleted_at TIMESTAMP DEFAULT NULL
);

INSERT INTO users (name, email, password) VALUES
('一郎', 'ichiro@example.com', 'password'),
('二郎', 'jiro@example.com', 'password'),
('三郎', 'saburo@example.com', 'password');

CREATE TABLE tasks (
    task_id serial PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    deleted_at TIMESTAMP DEFAULT NULL
);

INSERT INTO tasks (user_id, title, description, status) VALUES
(1, 'タスク1', 'タスク1の本文', 'NOT_STARTED'),
(2, 'タスク2', 'タスク1の本文', 'IN_PROGRESS'),
(3, 'タスク3', 'タスク1の本文', 'DONE');

