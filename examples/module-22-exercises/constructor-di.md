# Lab 22 — Constructor Injection Preference

## Preferred pattern
Constructur with final CustomerRepository and NotificationService.

## Why (testability)
Required dependencies are explicit. Unit tests can mock fakeRepo and fakeNotif

## Avoid
@Autowired as primary pattern

## Setter role (one line)
Optional. 

## Scope
Pre-lab only.