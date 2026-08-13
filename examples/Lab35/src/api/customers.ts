import type { Customer } from '../types/customer'
import { http } from './http'

export const customersApi = {
  list: (signal?: AbortSignal) =>
      http<Customer[]>("/api/customers", { signal }),
  create: (draft: CustomerDraft) =>
      http<Customer>("/api/customers", {
        method: "POST",
        body: JSON.stringify(draft),
      }),
  update: (id: string, draft: CustomerDraft) =>
      request<Customer>(`/customers/${encodeURIComponent(id)}`, {
        method: "PUT",
        body: JSON.stringify(draft),
      }),
  get(customerId: string, signal?: AbortSignal): Promise<Customer> {
    // TODO: GET /api/customers/{id}
    return http<Customer>(`/api/customers/${customerId}`, {}, signal)
  },
}
