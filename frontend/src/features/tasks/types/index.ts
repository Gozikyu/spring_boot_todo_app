export type Task = {
  taskId: number;
  userId: number;
  title: string;
  description: string;
  status: string;
};

export type NewTask = Omit<Task, 'taskId'>;

/**サーバー側のタスク型。サーバー側のやり取りはこの型を使う。（型統一すべき） */
export type ServerTask = Omit<Task, 'userId'> & { user: { userId: number } };
