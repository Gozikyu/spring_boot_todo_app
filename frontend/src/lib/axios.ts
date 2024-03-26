import Axios, { AxiosError, AxiosResponse } from 'axios';

import { API_URL } from '@/config';

export const axios = Axios.create({
  baseURL: API_URL,
});

const responseInterceptor = (response: AxiosResponse) => {
  return response.data;
};

const errorInterceptor = (error: AxiosError) => {
  return Promise.reject(error);
};

axios.interceptors.response.use(responseInterceptor, errorInterceptor);
