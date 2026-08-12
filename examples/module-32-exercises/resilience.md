# Lab 32 — Why Resilience

## Step 1 — Scenario

Customer detail for `CUS-1001` Amina calls Account Profile. The dependency hangs 30s. List three user-visible or thread-pool effects.

## Step 2 — Pattern names
Retry, Circuit breaker, Time limiter, Fallback.

## Step 3 — Not a substitute

Resilience wraps calls; it does not fix a permanently wrong URL.
