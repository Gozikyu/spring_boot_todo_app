import { axios } from '@/lib/axios';
import { useMutation } from 'react-query';
import { NewTask, ServerTask } from '../types';
import { queryClient } from '@/lib/react-query';

export type CreateTaskVariables = {
  data: NewTask;
};

const createTask = ({ data }: CreateTaskVariables) => {
  const serverTask: Omit<ServerTask, 'taskId'> = {
    user: { userId: data.userId },
    title: data.title,
    description: data.description,
    status: data.status,
  };
  return axios.post(`/${data.userId}/tasks`, serverTask);
};

export const useCreateTask = () => {
  return useMutation({
    mutationFn: createTask,
    onSuccess: () => {
      queryClient.invalidateQueries('tasks');
    },
    onError: (error) => {
      console.log(`mutation error ${error}`);
    },
  });
};
