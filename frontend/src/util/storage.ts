const storagePrefix = 'todo_app_';

const storage = {
  getUserId: (): string => {
    return JSON.parse(
      window.localStorage.getItem(`${storagePrefix}userId`) as string
    );
  },
  //TODO: JWT認証をするようにしたい
  setUserId: (userId: string) => {
    // window.localStorage.setItem(`${storagePrefix}token`, JSON.stringify(token));
    window.localStorage.setItem(
      `${storagePrefix}userId`,
      JSON.stringify(userId)
    );
  },
  clearTokenAndUserId: () => {
    window.localStorage.removeItem(`${storagePrefix}token`);
    window.localStorage.removeItem(`${storagePrefix}userId`);
  },
};

export default storage;
