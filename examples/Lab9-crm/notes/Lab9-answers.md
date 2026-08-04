# Security Review

1. Which inputs are untrusted? (Downloaded Maven artifacts; later API inputs)
> Any external input, including downloaded Maven artifacts and future API inputs, should be treated as untrusted.
2. Where are authn/authz/validation enforced later? (App layers + CI/repo managers)
> Authentication, authorization and validation should be enforced at the application layers, and during contiuous integration and repository management processes.
3. Which values are sensitive, and where stored? (Never in POM; use secrets stores)
> Sensitive values, such as API keys and passwords should never exist in the POM. They should instead be stored in a secure secret store, e.g. an environment variable manager.


# Reflection Questions
1. Which design decision most affected build correctness?
There wasn't a notable design decision that most clearly impacted build correctness. I would argue towards the decision of the scope for each dependency.
This decision limits which dependencies are brought to production, which are used for testing and so on.
2. What evidence proves the lifecycle walk was real (not only package once)?
The lifecycle walk was real, as evidenced by walking through the maven lifecycle. Seeing each step from validation to installation showed me that the lifecycle walk was real.
3. Which failure was hardest to diagnose?
The hardest failure to diagnose was spotting the JUnit scope error. Forgetting the scope tag seems relatively easy, and the dependency tree is not my first thing to check before production.
