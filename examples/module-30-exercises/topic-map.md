# Lab 30 — Topic and Key Map

## Reference

| Concept | Northstar choice |
| --- | --- |
| Main topic | crm.customer-events.v1 |
| DLQ topic | crm.customer-events.v1.dlq |
| Partitions (lab) | 3 |
| Record key | customerId (e.g. CUS-1001) |

## Step 2 — Keying reason

Write why keying by `CUS-1001` / `CUS-1002` keeps a customer's events ordered within a partition.

## Step 3 — Versioning

Explain what the `.v1` suffix buys the team when the payload schema changes later.

## Step 4 — DLQ trigger

List two failure cases that should land a record in the DLQ (conceptual only).

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.