# Lab 22 — Bean Lifecycle Callbacks

## Lifecycle order
Create → Inject → @PostConstruct → Use → @PreDestroy

## @PostConstruct purpose
Initialize the bean after dependencies are injected. Any other initial setup.

## @PreDestroy purpose
Clean up/close resourses before bean is destroyed. Gracefull destroy.

## What not to do in init
Do not put request logic in @PostConstruct.

## Scope
Pre-lab only.