# Lab 30 — Why Async for CRM

## Step 1 — List sync pain

Customer service creates `CUS-1001` Amina Khan over HTTP with correlation `lab-request-001`. List **three** problems if it also calls email, audit, and analytics synchronously in the same request thread.

## Step 2 — Event idea

In one sentence, describe publishing a `CustomerCreated` event so other teams consume independently.

## Step 3 — Coupling check

Mark true/false: *The Customer JVM must be up for the Audit consumer to process an already-published event.*

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.