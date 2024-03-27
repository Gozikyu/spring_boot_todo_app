const storagePrefix = 'todo_app_';

const storage = {
  getUserId: (): number => {
    return window.localStorage.getItem(
      `${storagePrefix}userId`
    ) as unknown as number; // アサーションしないようにしたいが、自分で設定する値なので大丈夫なはず
  },
  //TODO: JWT認証をするようにしたい
  setUserId: (userId: number) => {
    // window.localStorage.setItem(`${storagePrefix}token`, JSON.stringify(token));
    window.localStorage.setItem(
      `${storagePrefix}userId`,
      JSON.stringify(userId)
    );
  },
  clearUserId: () => {
    window.localStorage.removeItem(`${storagePrefix}userId`);
  },
};

export default storage;
