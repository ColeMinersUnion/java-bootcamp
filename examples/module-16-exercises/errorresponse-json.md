# Lab 16 — ErrorResponse JSON Draft

## Fields
timestamp, status, error, message, path, correlationId.


## Sample (CUS-9999)
```json
{ "status":"Not Found" , "message": "customer was not found", "correlationId": "not_found_error" }
```