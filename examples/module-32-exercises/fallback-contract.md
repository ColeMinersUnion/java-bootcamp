# Lab 32 — Fallback Contract

## Step 1 — Fields kept

List fields still shown: customerId, displayName maybe, status UNKNOWN.

## Step 2 — Fields dropped

List fields omitted: balance, tier, lastLogin.

## Step 3 — API signal

Decide: HTTP 200 with `degraded=true` vs 503 — pick one and justify.
API Signal should be 503. 200 responses look clean and may get by some filters. I find that it's best practice to label more things 
as errors, not less. 

## Step 4 — User message

Draft one UI string: *Account details temporarily limited.*
