# Lab 30 — Producer Checklist

## Step 1 — Settings list

Write a checklist: `acks=all`, idempotent producer, key = customerId, value = JSON envelope.

## Step 2 — Why acks=all

One sentence: wait for ISR ack before considering the CRM event durable.

## Step 3 — Idempotence

One sentence: broker dedupes producer retries so Amina is not double-created in the log.

## Step 4 — Out of scope today

Mark: *Do not run `kafka-console-producer` in this pre-lab.*

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.