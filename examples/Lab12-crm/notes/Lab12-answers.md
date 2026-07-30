## Discussion Questions
Main data flow after refactor (create / get / update status)
Trust boundary and where validation lives after cleanup
Success/failure contract (duplicate ID, unknown ID, blank name)
Stable identity (CUS-1001) vs mutable fields (status, email)
Retry/idempotency implications for create vs get
Local in-memory shortcut vs production persistence
Logs/evidence for support (lab-request-001)
Two JVM instances = independent memory (conflict risk)
Which SOLID ideas fit this lab’s size, and which are deferred?
Why freezing a before snapshot matters more than “I rewrote it cleanly”?