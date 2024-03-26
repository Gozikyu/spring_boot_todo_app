import { useQuery } from 'react-query';

import { axios, axiosForUnauthenticated } from '@/lib/axios';
import { ExtractFnReturnType, QueryConfig } from '@/lib/react-query';
import { Task } from '../types';

const getTasks = (userId: string): Promise<Task[]> => {
  return axiosForUnauthenticated.get(`/tasks`);
};

type QueryFnType = typeof getTasks;

type UseUsersOptions = {
  config?: QueryConfig<QueryFnType>;
};

export const useTasks = (userId: string, { config }: UseUsersOptions = {}) => {
  return useQuery<ExtractFnReturnType<QueryFnType>>({
    ...config,
    queryKey: ['tasks'],
    queryFn: () => getTasks(userId),
  });
};
