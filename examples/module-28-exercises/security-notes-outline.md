# Lab 28 — Production IdP Checklist

## IdP note
Prefer IdP / Oauth2 in production 

## Key rotation
Store signing keys in a secret manager; rotate on schedule/incident

## Transport / TTL
Short token TTL; HTTPS only

## Logging hygiene
Audit failed logins; never log raw bearer tokens


## Scope
Pre-lab only. No real secrets.