
### Step 1 — Complete the map

| Type | Package | Fully qualified name |
| ---- |---------| -------------------- |
| `CustomerController` | com.    | |
| `CustomerService` |         | |
| `CustomerRepository` |         | |
| `Customer` |         | |
| `CustomerRequest` |         | |
| `AppConfig` |         | |
| `CustomerNotFoundException` |         | |


```text
com.northstar.crm.controller.CustomerController
com.northstar.crm.service.CustomerService
com.northstar.crm.repository.CustomerRepository
com.northstar.crm.entity.Customer
com.northstar.crm.dto.CustomerRequest
com.northstar.crm.config.AppConfig
com.northstar.crm.exception.CustomerNotFoundException
```

### Step 3 — Translate package to path

```java
package com.northstar.crm.service;
```

the production source path must be:

```text
src/main/java/com/northstar/crm/service/
```

Write the equivalent path for `CustomerRequest`: 
```text
src/main/java/com/northstar/crm/dto/CustomerRequest.java
```

### Step 4 — Correct bad names

| Bad | Correct |
| --- | ------- |
| `com.Northstar.CRM.Service` | `com.northstar.crm.service` |
| package `utils` for customer business rules | `service` or a focused domain package |
| `customer_service.java` | `CustomerService.java` |
| package declaration does not match folders | Make both paths identical |
