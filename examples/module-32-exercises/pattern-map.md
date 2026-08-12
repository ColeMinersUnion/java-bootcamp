# Lab 32 — Pattern Map

## Reference

| Pattern | CRM use |
| --- | --- |
| Retry | Transient 503 from Account Profile |
| TimeLimiter | Fail fast if call exceeds N ms |
| CircuitBreaker | Stop calling when failure rate high |
| Fallback | Return cached/minimal profile for Amina |

## Step 2 — Add Ravi row

Add one example sentence for `CUS-1002` Ravi when circuit is open.

## Step 3 — Order idea

Propose decorator order in one line (e.g. TimeLimiter → CircuitBreaker → Retry → call).

## Step 4 — Boundary

Mark: do not apply circuit breaker to local in-memory map lookups.
