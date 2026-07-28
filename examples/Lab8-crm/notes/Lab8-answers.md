# Reflection Questions

### Which design decision most affected correctness of the skeleton?



### Which failure was hardest to diagnose (pathing, packages, POM)?
The pathing failure was the hardest to diagnose. When I created a `.java` file in an unsupported path,
maven compiled without error. The POM and package failures threw errors upon compilation. 

### What evidence proves the layered structure is real, not only aspirational?
The way maven handled compilation was the strongest evidence that the layered structure was real.
Maven was able to demand the file structure, and built the project without issue. 

### What breaks first at ten times the team size if packages are messy?
In a large team, readability is the first to go with a messy package structure. Code becomes hard to find,
program flow becomes needlessly complex, and maintainability suffers. Messy packages may break imports and cause
package failures, as well as many other failures due to poor coding standards. 

### Which concern should move to shared infrastructure later?
Concerns like PostgresSQL and Spring Boot should be handles as part of a larger intiative towards building shared infrastructure.

### What must change before real customer data is used?
A lot should change before real customer data is used. Customer data should be store in a database, and the infrastructure should be built out. 
Private data needs to be behind some security features to protect data privacy. 

### How does this lab connect to Labs 9–12 and later CRM platform pieces?
This lab is all about defining the structure for later CRM platform pieces. It shows us how code and the larger repository should be structured
for maintainability. Later labs will use this structure to show off other key pieces of the CRM platform. 

### What metric, log field, query plan, or UI state matters most once APIs exist?
Once the API exists, 

### Why keep DTOs separate from entities for creating Amina Khan (CUS-1001)?
DTOs are meant to transfer data objects between layers. As such, their purpose is not to create data like customer 1001, but rather to transfer it from one layer to another.
### (Forward look) When Spring Boot arrives, which packages stay stable vs which files change first?

