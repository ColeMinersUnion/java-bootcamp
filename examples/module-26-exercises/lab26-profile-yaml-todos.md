# Lab 26 — YAML TODOs

Files: `application.yaml`, `application-dev.yaml`, `application-test.yaml`, `application-prod.yaml`
Base: spring.application.name, server.port
dev: logging.level=DEBUG
prod secret pattern: spring.datasource.password: ${DB_PASSWORD}

> Never commit passowrds nor environmental variables. 