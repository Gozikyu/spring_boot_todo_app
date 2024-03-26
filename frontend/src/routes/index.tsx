import { useRoutes } from 'react-router-dom';
import { protectedRoutes } from './protected';
import { publicRoutes } from './public';
import storage from '@/util/storage';
import Landing from '@/features/misc/routes/Landing';

export const AppRoutes = () => {
  const userId = storage.getUserId();

  const commonRoutes = [{ path: '/', element: <Landing /> }];
  const routes = userId ? protectedRoutes : publicRoutes;

  const elements = useRoutes([...routes, ...commonRoutes]);

  return <>{elements}</>;
};
