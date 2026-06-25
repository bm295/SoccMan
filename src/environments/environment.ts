interface AngularLocalEnv {
  readonly NG_APP_SUPABASE_URL?: string;
  readonly NG_APP_SUPABASE_ANON_KEY?: string;
  readonly NG_APP_SENTRY_DSN?: string;
}

const env = (import.meta as ImportMeta & { readonly env?: AngularLocalEnv }).env ?? {};

export const environment = {
  production: false,
  supabase: {
    url: env.NG_APP_SUPABASE_URL ?? '',
    anonKey: env.NG_APP_SUPABASE_ANON_KEY ?? '',
  },
  sentry: {
    dsn: env.NG_APP_SENTRY_DSN ?? '',
  },
} as const;
