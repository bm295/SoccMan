export interface AuthState {
  readonly userId: string | null;
  readonly role: string | null;
}

export const initialAuthState: AuthState = {
  userId: null,
  role: null,
};
