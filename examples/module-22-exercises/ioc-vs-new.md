# Lab 22 — IoC Versus Manual Wiring

| Approach | Who creates collaborators? | Test impact |
| --- |----------------------------| --- |
| Manual `new` | The object                 | _____ |
| IoC / DI | The framework/dependency   | _____ |

## Smell (one sentence)
CustomerService owns `new InMemoryCustomerRepository()`.

## Fix (one sentence)
Pass repo through constructor arguments; let the container (or test) supply it.


## Scope
Pre-lab only.