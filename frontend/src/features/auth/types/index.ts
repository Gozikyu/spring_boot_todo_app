export type LoginResponse = {
  // token: string;
  userId: number;
};

export function isLoginResponse(obj: any): obj is LoginResponse {
  return (
    typeof obj === 'object' &&
    obj !== null &&
    'userId' in obj &&
    typeof obj.userId === 'number'
  );
}
