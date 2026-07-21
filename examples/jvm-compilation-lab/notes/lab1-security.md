# Security Questions

1. Bytecode as artifact: Why might an enterprise forbid copying raw .class files between machines without a reproducible build (Maven/CI)?
2. Heap dumps: If a future CRM JVM writes a heap dump on OOM, what privacy risk appears if customer PII sits in memory? Who should access dumps? 
3. Logging secrets: Why must you never print passwords or cloud access keys—even in a tiny training main? 
4. Classpath trust: What risk exists if a malicious Employee.class appears earlier on the classpath than your build output? 
5. Flag / container limits: Why is casually setting -Xmx without matching container memory limits dangerous in production? 
6. Production delta: Name three controls Northstar would add before real customer workloads (non-root user, memory limits, CI-signed artifacts, no secrets in logs).


## Security Answers
1. Raw `.class` files might create security risks. The code is generally hard to read, and may include memory leaks and security vulnerabilities. 
2. I don't believe we went over this.
3. Plaintext passwords or access keys may become visible to users or other third parties. Logging is not considered secure for sensitive information.
4. A malicious `.class` file might pre-empt the intended program flow. It would run any code required for loading before the build output.
5. Setting -Xmx without matching container memory limits might cause an overflow, especially if the set -Xmx is larger than the container memory.
6. I also don't remember going over northstar controls. 