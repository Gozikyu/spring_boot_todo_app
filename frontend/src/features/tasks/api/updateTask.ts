import { axios } from '@/lib/axios';
import { useMutation } from 'react-query';
import { ServerTask, Task } from '../types';
import { queryClient } from '@/lib/react-query';

export type UpdateTaskVariables = {
  data: Task;
};

const updateTask = ({ data }: UpdateTaskVariables) => {
  const serverTask: ServerTask = {
    taskId: data.taskId,
    user: {
      userId: data.userId,
    },
    title: data.title,
    description: data.description,
    status: data.status,
  };
  return axios.put(`/${data.userId}/tasks/${data.taskId}`, serverTask);
};

export const useUpdateTask = () => {
  return useMutation({
    mutationFn: updateTask,
    onSuccess: () => {
      queryClient.invalidateQueries('tasks');
    },
    onError: (error) => {
      console.log(`mutation error ${error}`);
    },
  });
};
