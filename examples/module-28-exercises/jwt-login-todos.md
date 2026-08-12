# Lab 28 — JWT Login TODOs

## Login Path & Body
POST /api/auth/login {username,password} → {accessToken, tokenType}
## Token Response
JwtService issueToken / parseSubject / parseRole (lab stub OK)
## Bearer Header Form
Client: Authorization: Bearer <accessToken>
## Lab Users/form
Lab users: agent1 (AGENT), admin1 (ADMIN)

## Secret Handling
Secret: env JWT_SECRET → northstar.security.jwt-secret (placeholder in .env.example)

## Scope
Pre-lab only.