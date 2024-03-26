import { axios } from '@/lib/axios';
import { LoginResponse, isLoginResponse } from '..';
import storage from '@/util/storage';

export type LoginVariables = {
  email: string;
  password: string;
};

const loginWithEmailAndPassword = async (
  data: LoginVariables
): Promise<LoginResponse> => {
  const res = await axios.post(`/login`, data);

  if (!isLoginResponse(res)) {
    throw new Error('responseがLoginResponseの型ではありません');
  }
  return res;
};

export const loginAndSetUserId = async (data: LoginVariables) => {
  const response = await loginWithEmailAndPassword(data);
  const { userId } = response;
  storage.setUserId(userId);
};
