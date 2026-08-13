import { ApiError } from './ApiError'

const API_URL = import.meta.env.VITE_API_BASE_URL as string

export async function http<T>(
  path: string,
  init: RequestInit = {}
): Promise<T> {
  const response = await fetch(`${API_URL}${path}`, {
    ...init,
    headers: {
      "Content-Type": "application/json",
      "X-Correlation-Id": "lab-request-001",
      ...init.headers,
    },
  });
  if (!response.ok) throw await ApiError.from(response);
  if (response.status === 204) return undefined as T;
  return response.json() as Promise<T>;
}
