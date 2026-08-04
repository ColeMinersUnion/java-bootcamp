# Reflection Questions (new)
1. Which design decision most affected correctness of the skeleton?
While the layered architecture is a result of several key design decisions, I would posit that the most impactful 
design decision was the separation of concerns: separating code into
- `config/`
- `controller/`
- `entity/`
- `exception/`
- `repository/`
- `service/`
2. What evidence proves the layered structure is real, not only aspirational?
The evidence that this layered structure is real, and not just some aspirational best practice is the way maven, `mvn`, is able to
successfully parse, compile and test this code without extensive scripting. 

3. Which failure was hardest to diagnose (pathing, packages, POM)?
The hardest failure to diagnose was the third failure experiment: a misplaced `.java` file in `src/`. 
Compilation still succeeded, but maven did not compile the misplaced file. Without searching for the class file, I may 
have missed this failure entirely.

# Security Questions
1. Which browser, network, event, or database inputs are untrusted? (Design: future API inputs)
Any and all external inputs should be treated as untrusted. This includes browser inputs, networks inputs, events and databases. 

2. Where are authentication, authorization, and validation enforced? (Which layer will own them?)
Authentication, authorization, and validation should be enforced at the service layer to ensure that only authorized users can access certain resources and that data is properly validated before being processed.

3. Which values are sensitive, and where are they stored? (None in Lab 8—keep it that way)
Sensitive values should be stored in secure locations, such as environment variables or secure databases. These values include personal or identifiable information about users.
