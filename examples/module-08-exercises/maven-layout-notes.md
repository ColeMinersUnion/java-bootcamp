## Reference Tree

```text
customer-management-platform/
├── pom.xml
├── docs/
│   └── CODING-STANDARDS.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/northstar/crm/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/
│       │   └── com/northstar/crm/
│       └── resources/
└── target/
```

| File | Destination                      |
| ---- |----------------------------------|
| `Customer.java` | `src/main/java/com/northstar/crm |
| `CustomerServiceTest.java` | src/main/java/com/northstar/crm  |
| `application.properties` | src/main/resources               |
| `sample-customers.json` used only by tests | src/test/resources/              |
| `CODING-STANDARDS.md` | docs/                            |
| `Customer.class` | target/classes/                  |


> `target/` is generated from source by Maven. It can be deleted and rebuilt, so it should be ignored rather than committed.

## Explain why each is wrong:

- production Java in `src/test/java`;
- passwords committed in `application.properties`;
- hand-editing `target/classes`;
- test fixtures in production resources without a runtime need.

Production should be generated separately from the testing suite. The production environment should be clean, and testing should not be influenced by the production output.

Passwords should not be committed in `application.properties`. Passwords need to be secure, and should be kept behind a hash, presumably in a database.

`Target/classes` needs to be a direct representation of main/java... for readability, reproducability and collaborative work.

Testing should always be done in an isolated environment. The testing fixtures in production can cause unnecessary bloat and obfuscate what is needed or not needed in production.

