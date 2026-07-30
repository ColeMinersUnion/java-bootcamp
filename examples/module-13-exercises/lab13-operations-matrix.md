## Get Customer:
- In: customerId
- Out: id, name, status | Fault: not found

## Activate Customer:
- In: customerId (+ correlation header idea)
- Out: new status | Fault: invalid transition