# Lab 28 — MockMvc Evidence Matrix

| Case | Auth           | Route                  | Expect |
| --- |----------------|------------------------|--------|
| Anonymous customers | None           | GET /api/customer-1001 | 401    |
| Agent admin | AGENT          | GET /api/admin         | 403    |
| Agent customer | AGENT Bearer   | GET /api/customer-1001 | 200    |
| Bad token | garbage bearer | GET customers          | 401    |

## Scope
Pre-lab only.