import { useQuery } from 'react-query';

import { axios } from '@/lib/axios';
import { ExtractFnReturnType, QueryConfig } from '@/lib/react-query';
import { ServerTask, Task } from '../types';

const getTasks = async (userId: number): Promise<Task[]> => {
  const res = await axios.get<ServerTask[]>(`/${userId}/tasks`);
  return res.data.map((task) => ({
    taskId: task.taskId,
    userId: task.user.userId,
    title: task.title,
    description: task.description,
    status: task.status,
  }));
};

type QueryFnType = typeof getTasks;

type UseUsersOptions = {
  config?: QueryConfig<QueryFnType>;
};

export const useTasks = (userId: number, { config }: UseUsersOptions = {}) => {
  return useQuery<ExtractFnReturnType<QueryFnType>>({
    ...config,
    queryKey: ['tasks'],
    queryFn: () => getTasks(userId),
  });
};
