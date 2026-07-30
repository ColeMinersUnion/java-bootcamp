
| Question | Your answer      |
| -------- |------------------|
| What is the `groupId`? | com.northstar    |
| What is the `artifactId`? | customer-service |
| What is the `version`? | 0.1.0-SNAPSHOT   |
| What is the packaging? | jar              |
| Write the full GAV (`groupId:artifactId:version`) |     `com.northstar:customer-service:0.1.0-SNAPSHOT`             |


> A `-SNAPSHOT` version means the artifact is still under active development and may change without a new release number.


Explain why each is wrong for Northstar CRM Lab 9:

- `groupId` set to `com.example` while the Java packages are `com.northstar.crm`;

The group ID does not reflect the Java packages. 

- `artifactId` set to `CustomerService` (PascalCase);

The GAV string does not use pascal case. 

- omitting `<packaging>` and assuming WAR for a plain Java library/app JAR;

Assuming a web archive is dangerous as it may not exist, and some design contraints may stipulate the project compiles and runs offline.

- committing a different `version` on every laptop with no team agreement.

This causes a nightmare for bug reports and version control. WIthout a synchronized version, it's impossible to tell when bugs are new. The project becomes much harder to maintain, as maintainers may be developing for the wrong version.