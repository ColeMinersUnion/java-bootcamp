# Banking domain notes

| Entity | Identity | Important attributes | Main responsibility |
| ------ | -------- | -------------------- | ------------------- |
| Customer | customerId | name, email, phone | Maintain customer profile |
| Account | accountNumber | owner, balance, accountType | Protect balance and perform deposits/withdrawals |
| Transaction | transactionId | account, type, amount, timestamp | Record one account operation |

## Relationships

- One Customer can own zero or more Accounts.
- One Account belongs to exactly one Customer.
- One Account can have many Transactions.
- One Transaction belongs to exactly one Account.

## Rules

- An account balance cannot be changed directly from outside Account.
- A deposit amount must be positive.
- A withdrawal cannot exceed the allowed balance.

## Why should `Account`, rather than `Main`, decide whether a withdrawal is valid?
Withdrawals occur in relation to an account, i.e. you withdrawal money from an account. 
As such, the validaiton of a withdrawal should occur in `Account` since it only pertains to the instance of an account
and the validation logic can be generalized to all `Account` instances.