# AI review notes — Lab 10

## lab10-001 — weak vs strong (entity)
- Date: 07/26/2026
- Weak prompt used: 
> //Customer Class
- Output summary: A simple customer class with fields that ultimately were not reflective of our desired product.
- Strong prompt used: 
> Java entity class Customer in package com.northstar.crm.entity representing a Northstar CRM customer. Fields: customerId (String, format "CUS-1001"), fullName (String), email (String), phone (String), status (CustomerStatus enum: PROSPECT, ACTIVE, SUSPENDED, CLOSED), createdAt (LocalDateTime). No-args constructor, all-args constructor, getters and setters, equals/hashCode based only on customerId, toString.
- Output summary:
 great skeleton for a customer class that represents the needs for that class.

- Decision: accept / reject / partial
- Reason (1 sentence):

## lab10-002 — weak vs strong (addCustomer)
- Date:
- Decision: accept / reject / partial
- Reason:

## lab10-003 — CustomerStatus / Customer scaffold
- Rejected JPA? yes / no
- Notes:

## lab10-004 — CustomerService review
- Notes:
