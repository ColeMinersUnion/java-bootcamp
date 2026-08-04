# Lab 16 — Catch Order

## Step 1 — List types
- BusinessException
- Validation failures
- Exception

## Step 2 — Order (top → bottom)
1. BusinessException
2. Validation 
3. Exception

## Step 3 — Why
We want to catch exceptions with as much granularity as possible. Catching businessException tells us the most about
the error, and catching an Exception would tell us the least about the error.

## Scope
Pre-lab only.