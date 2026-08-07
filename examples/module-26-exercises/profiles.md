# Lab 26 — Profile Purposes

| Profile | Purpose                                       |
| --- |-----------------------------------------------|
| dev | Local version for development.                |
| test | Surefire isolation                            |
| prod | Production profile for secrets and deployment |

## One risk if prod uses dev YAML
Failure may be slow. Production may not make the proper connections, e.g. connecting to the DB.

## Scope
Pre-lab only.