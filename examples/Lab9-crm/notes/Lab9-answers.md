
# Security and Production Review

### Which inputs are untrusted? (Downloaded Maven artifacts; later API inputs)



### Where are authn/authz/validation enforced later? (App layers + CI/repo managers)
Which values are sensitive, and where stored? (Never in POM; use secrets stores)
What can be retried safely? (mvn verify, snapshot install)
What happens after a partial failure? (Failed test stops verify; no bad promotion in CI)
What would an operator monitor? (CI duration, failed verify jobs)
Which local default is unacceptable in production? (dev profile active by default with real secrets—never do that)
How are contracts versioned? (Artifact version + later OpenAPI/WSDL)

# Reflection Questions


### Which design decision most affected build correctness?


Which failure was hardest to diagnose?
What evidence proves the lifecycle walk was real (not only package once)?
What breaks first at ten times the dependency count?
Which concern should move to shared infrastructure (artifact repository, CI cache)?
What must change before real customer data is used?
How does this lab connect to Lab 8 structure and Lab 10+ code?
What metric, log field, or CI signal matters most when verify fails?
Why is test scope on JUnit more than a style preference?
(Forward look) When Spring Boot arrives, what stays stable in this POM vs what changes first?