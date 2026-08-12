# Lab 28 — SecurityFilterChain Sketch

## Session policy
STATELESS

## Login matcher
Permit All

## Customers matcher + roles
hasAnyRole()

## Admin matcher + roles
hasRole(Admin)

Other APIs → authenticated (default deny extras)

JWT filter before UsernamePasswordAuthenticationFilter.

## Scope
Pre-lab only.